package com.example.gestion_bibliotheque.dto.loan;

import com.example.gestion_bibliotheque.enums.LoanType;

import java.time.LocalDate;

public class BorrowRequest {
    private Long userId;
    private Long bookId;
    private LoanType loanType;
    private LocalDate StartDate;

    // Getters / Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LoanType getLoanType() {
        return loanType;
    }
    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
    public LocalDate getStartDate() {
        return StartDate;
    }
    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }
}

