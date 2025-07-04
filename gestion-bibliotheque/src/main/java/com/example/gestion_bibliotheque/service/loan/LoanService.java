package com.example.gestion_bibliotheque.service.loan;


import com.example.gestion_bibliotheque.entity.loan.Loan;
import com.example.gestion_bibliotheque.enums.LoanType;
import com.example.gestion_bibliotheque.exception.BusinessException;

import java.time.LocalDate;

public interface LoanService {

    Loan borrowBook(Long userId, Long bookId, LoanType loanType, LocalDate start_date) throws BusinessException;

    Loan returnBook(Long loanId);
    Loan extendLoan(Long loanId);
}
