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
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @GetMapping
    public ResponseEntity<?> getAllLoans() {
        List<Loan> loans = loanService.getLoans();
        return ResponseEntity.ok(loans);
    }
    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        try {
            Loan loan = loanService.borrowBook(
                    request.getUserId(),
                    request.getBookId(),
                    request.getLoanType(),
                    request.getStartDate()
            );
            return ResponseEntity.ok(loan);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{loanId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long loanId, @RequestBody ReturnLoanRequest request) {
        try {
            Loan returnedLoan = loanService.returnBook(loanId, request.getReturnDate());
            return ResponseEntity.ok(returnedLoan);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
