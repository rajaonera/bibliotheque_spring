package com.example.gestion_bibliotheque.repository.loan;

import com.example.gestion_bibliotheque.entity.loan.LoanPolicy;
import com.example.gestion_bibliotheque.enums.LoanType;
import com.example.gestion_bibliotheque.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanPolicyRepository extends JpaRepository<LoanPolicy, Long> {
    Optional<LoanPolicy> findByUserRoleAndLoanType(UserRole userRole, LoanType loanType);
}