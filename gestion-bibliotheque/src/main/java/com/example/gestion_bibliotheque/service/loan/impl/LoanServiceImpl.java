package com.example.gestion_bibliotheque.service.loan.impl;

import com.example.gestion_bibliotheque.entity.book.Book;
import com.example.gestion_bibliotheque.entity.book.BookCopy;
import com.example.gestion_bibliotheque.entity.loan.Loan;
import com.example.gestion_bibliotheque.entity.loan.LoanPolicy;
import com.example.gestion_bibliotheque.entity.loan.Penalty;
import com.example.gestion_bibliotheque.entity.logs.ActivityLog;
import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.enums.CopyStatus;
import com.example.gestion_bibliotheque.enums.LoanType;
import com.example.gestion_bibliotheque.exception.BusinessException;
import com.example.gestion_bibliotheque.repository.book.BookCopyRepository;
import com.example.gestion_bibliotheque.repository.book.BookRepository;
import com.example.gestion_bibliotheque.repository.loan.HolidayRepository;
import com.example.gestion_bibliotheque.repository.loan.LoanRepository;
import com.example.gestion_bibliotheque.repository.loan.PenaltyRepository;
import com.example.gestion_bibliotheque.repository.logs.LogRepository;
import com.example.gestion_bibliotheque.repository.user.UserRepository;
import com.example.gestion_bibliotheque.service.loan.DateUtils;
import com.example.gestion_bibliotheque.service.loan.LoanPolicyService;
import com.example.gestion_bibliotheque.service.loan.LoanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.time.DayOfWeek;


@Service
public class LoanServiceImpl implements LoanService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final LoanRepository loanRepository;
    private final LoanPolicyService loanPolicyService;
    private final HolidayRepository holidayRepository;
    private final PenaltyRepository penaltyRepository;
    private final LogRepository logRepository;

    public LoanServiceImpl(
            UserRepository userRepository,
            BookRepository bookRepository,
            BookCopyRepository bookCopyRepository,
            LoanRepository loanRepository,
            LoanPolicyService loanPolicyService,
            HolidayRepository holidayRepository,
            PenaltyRepository penaltyRepository,
            LogRepository logRepository
    ) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.loanRepository = loanRepository;
        this.loanPolicyService = loanPolicyService;
        this.holidayRepository = holidayRepository;
        this.penaltyRepository = penaltyRepository;
        this.logRepository = logRepository;
    }

    public static boolean isWeekendOrHoliday(LocalDate date ,HolidayRepository holidayRepository) {
        DayOfWeek day = date.getDayOfWeek();
        boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
        boolean isHoliday = holidayRepository.existsByDate(date);
        return isWeekend || isHoliday;
    }

    @Override
    public Loan borrowBook(Long userId, Long bookId, LoanType loanType, LocalDate start_date) throws BusinessException {
        // 1. Récupérer l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Utilisateur non trouvé"));

        // 2. Récupérer le livre
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Livre non trouvé"));

        // 3. Chercher un exemplaire disponible
        Optional<Object> availableCopyOpt = bookCopyRepository.findByBookAndStatus(book, CopyStatus.DISPONIBLE)
                .stream()
                .findFirst();

        if (availableCopyOpt.isEmpty()) {
            throw new BusinessException("Aucun exemplaire disponible");
        }

        BookCopy availableCopy = (BookCopy) availableCopyOpt.get();

        // 4. Règles métier via LoanPolicy
        LoanPolicy policy = loanPolicyService.findByUserRoleAndLoanType(user.getProfile(), loanType)
                .orElseThrow(() -> new BusinessException("Politique de prêt introuvable pour ce profil"));

        int currentLoans = loanRepository.countByUserAndReturnedFalse(user);
        if (currentLoans >= policy.getMaxLoans()) {
            throw new BusinessException("Nombre maximal de prêts atteint pour ce profil");
        }

        // 5. Créer le prêt
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBookCopy(availableCopy);
        loan.setStartDate(start_date);
        LocalDate dueDate  = DateUtils.calculateDueDate(start_date,policy);
        while (isWeekendOrHoliday(dueDate,holidayRepository )) {
            dueDate = dueDate.plusDays(1);
        }
        loan.setDueDate(dueDate);
        loan.setExtended(false);
        loan.setReturnDate(null);
        loan.setLoanType(loanType);

        // 6. Marquer le livre comme emprunté
        availableCopy.setStatus(CopyStatus.EMPRUNTE);
        bookCopyRepository.save(availableCopy);

        // 7. Enregistrer et retourner le prêt
        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId, LocalDate returnDate) throws BusinessException {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new BusinessException("Prêt introuvable"));

        if (loan.isReturned()) {
            throw new BusinessException("Le livre a déjà été retourné");
        }

        if (returnDate.isBefore(loan.getStartDate())) {
            throw new BusinessException("La date de retour ne peut pas être antérieure à la date de prêt");
        }

        // Mettre à jour le prêt
        loan.setReturned(true);
        loan.setReturnDate(returnDate);

        // Mettre l'exemplaire en DISPONIBLE
        BookCopy copy = loan.getBookCopy();
        copy.setStatus(CopyStatus.DISPONIBLE);
        bookCopyRepository.save(copy);

        // Vérifier le retard
        if (returnDate.isAfter(loan.getDueDate())) {
            int daysLate = (int) ChronoUnit.DAYS.between(loan.getDueDate(), returnDate);

            Penalty penalty = new Penalty();
            penalty.setUser(loan.getUser());
            penalty.setStartDate(returnDate);
            penalty.setEndDate(returnDate.plusDays(daysLate));
            penalty.setReason("Retour en retard de " + daysLate + " jour(s)");
            penalty.setActive(true);

            penaltyRepository.save(penalty);
        }

        // Logger l’action
        ActivityLog log = new ActivityLog();
        log.setUser(loan.getUser());
        log.setActionType("RETOUR");
        log.setDescription("Retour du livre : " + copy.getCode());
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);

        return loanRepository.save(loan);
    }

    @Override
    public Loan extendLoan(Long loanId) {
//        boolean isReserved = reservationRepository.existsByBookAndNotifiedFalse(bookCopy.getBook());
//        if (isReserved) {
//            throw new BusinessException("Impossible de prolonger : le livre est réservé par un autre utilisateur.");
//        }
        return null;
    }

    @Override
    public List<Loan> getLoans(LocalDate start_date) {
        return loanRepository.findAllByStartDate(start_date);
    }

    @Override
    public List<Loan> getLoans(LocalDate start_date, LocalDate end_date) {
        return loanRepository.findAllByStartDateAndAndDueDate(start_date,end_date);
    }

    @Override
    public List<Loan> getLoans(LocalDate start_date, LocalDate end_date, LoanType loanType) {
        return loanRepository.findAllByStartDateAndDueDateAndLoanType(start_date, end_date, loanType);

    }

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }
}
