package com.example.gestion_bibliotheque.entity.loan;

import com.example.gestion_bibliotheque.enums.UserRole;
import com.example.gestion_bibliotheque.enums.LoanType;
import jakarta.persistence.*;

@Entity
@Table(name = "loan_policies")
public class LoanPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanType loanType;

    @Column(nullable = false)
    private int maxLoans;

    @Column(nullable = false)
    private int loanDurationDays;

    @Column(nullable = false)
    private int maxProlongations;

    @Column(nullable = false)
    private boolean allowReservation;

    @Column(nullable = false)
    private boolean allowProlongation;

    @Column(nullable = false)
    private int penaltyDaysPerLateDay;

    // Constructeur vide
    public LoanPolicy() {
    }

    // Constructeur complet
    public LoanPolicy(Long id, UserRole userRole, LoanType loanType, int maxLoans, int loanDurationDays,
                      int maxProlongations, boolean allowReservation, boolean allowProlongation, int penaltyDaysPerLateDay) {
        this.id = id;
        this.userRole = userRole;
        this.loanType = loanType;
        this.maxLoans = maxLoans;
        this.loanDurationDays = loanDurationDays;
        this.maxProlongations = maxProlongations;
        this.allowReservation = allowReservation;
        this.allowProlongation = allowProlongation;
        this.penaltyDaysPerLateDay = penaltyDaysPerLateDay;
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public int getMaxLoans() {
        return maxLoans;
    }

    public void setMaxLoans(int maxLoans) {
        this.maxLoans = maxLoans;
    }

    public int getLoanDurationDays() {
        return loanDurationDays;
    }

    public void setLoanDurationDays(int loanDurationDays) {
        this.loanDurationDays = loanDurationDays;
    }

    public int getMaxProlongations() {
        return maxProlongations;
    }

    public void setMaxProlongations(int maxProlongations) {
        this.maxProlongations = maxProlongations;
    }

    public boolean isAllowReservation() {
        return allowReservation;
    }

    public void setAllowReservation(boolean allowReservation) {
        this.allowReservation = allowReservation;
    }

    public boolean isAllowProlongation() {
        return allowProlongation;
    }

    public void setAllowProlongation(boolean allowProlongation) {
        this.allowProlongation = allowProlongation;
    }

    public int getPenaltyDaysPerLateDay() {
        return penaltyDaysPerLateDay;
    }

    public void setPenaltyDaysPerLateDay(int penaltyDaysPerLateDay) {
        this.penaltyDaysPerLateDay = penaltyDaysPerLateDay;
    }
}
