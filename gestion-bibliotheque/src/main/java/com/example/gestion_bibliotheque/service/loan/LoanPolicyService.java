package com.example.gestion_bibliotheque.service.loan;

import com.example.gestion_bibliotheque.entity.loan.LoanPolicy;
import com.example.gestion_bibliotheque.enums.LoanType;
import com.example.gestion_bibliotheque.enums.UserRole;

import java.util.Optional;

public interface LoanPolicyService {

    Optional<LoanPolicy> findByUserRoleAndLoanType(UserRole userRole, LoanType loanType);

}
