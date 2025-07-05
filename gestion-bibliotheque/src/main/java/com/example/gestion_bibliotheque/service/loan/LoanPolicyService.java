package com.example.gestion_bibliotheque.service.loan;

import com.example.gestion_bibliotheque.entity.loan.LoanPolicy;
import com.example.gestion_bibliotheque.enums.LoanType;
import com.example.gestion_bibliotheque.enums.UserProfil;

import java.util.Optional;

public interface LoanPolicyService {

    Optional<LoanPolicy> findByUserRoleAndLoanType(UserProfil userProfil, LoanType loanType);

}
