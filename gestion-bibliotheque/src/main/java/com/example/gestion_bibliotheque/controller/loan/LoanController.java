package com.example.gestion_bibliotheque.controller.loan;

import com.example.gestion_bibliotheque.dto.loan.BorrowRequest;
import com.example.gestion_bibliotheque.dto.loan.ReturnLoanRequest;
import com.example.gestion_bibliotheque.entity.loan.Loan;
import com.example.gestion_bibliotheque.exception.BusinessException;
import com.example.gestion_bibliotheque.service.loan.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @GetMapping()
    public ResponseEntity<?> getAllLoans() {
        System.out.println("📣 getAllLoans() appelé !");
        List<Loan> loans = loanService.getLoans();
        return ResponseEntity.ok(loans);
    }
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        System.out.println("📣 borrowBook() appelé !");
        System.out.println("📣 Start Due : "+request.getStartDate());

        try {
            Loan loan = loanService.borrowBook(
                    request.getUserId(),
                    request.getBookId(),
                    request.getLoanType(),
                    request.getStartDate()
            );
            System.out.println("📣 End Due : "+loan.getDueDate());
            return ResponseEntity.ok(loan);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{loanId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long loanId, @RequestBody ReturnLoanRequest request) {
        System.out.println("📣 borrowBook() appelé !");
        try {
            Loan returnedLoan = loanService.returnBook(loanId, request.getReturnDate());
//            System.out.println("📣 Start Due : "+request.getStartDate());
            return ResponseEntity.ok(returnedLoan);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
