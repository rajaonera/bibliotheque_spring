package com.example.gestion_bibliotheque.service.loan.impl;

import com.example.gestion_bibliotheque.entity.loan.LoanPolicy;
import com.example.gestion_bibliotheque.enums.LoanType;
import com.example.gestion_bibliotheque.enums.UserProfil;
import com.example.gestion_bibliotheque.repository.loan.LoanPolicyRepository;
import com.example.gestion_bibliotheque.service.loan.LoanPolicyService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanPolicyServiceImpl implements LoanPolicyService {

    private final LoanPolicyRepository loanPolicyRepository;

    public LoanPolicyServiceImpl(LoanPolicyRepository loanPolicyRepository) {
        this.loanPolicyRepository = loanPolicyRepository;
    }

    @Override
    public Optional<LoanPolicy> findByUserRoleAndLoanType(UserProfil userProfil, LoanType loanType) {
        return loanPolicyRepository.findByUserProfilAndLoanType(userProfil, loanType);
    }
}
