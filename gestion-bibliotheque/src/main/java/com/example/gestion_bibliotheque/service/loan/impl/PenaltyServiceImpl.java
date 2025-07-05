package com.example.gestion_bibliotheque.service.loan.impl;

import com.example.gestion_bibliotheque.entity.loan.Penalty;
import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.repository.loan.PenaltyRepository;
import com.example.gestion_bibliotheque.service.loan.PenaltyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyServiceImpl implements PenaltyService {

    private final PenaltyRepository penaltyRepository;

    public PenaltyServiceImpl(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }
    @Override
    public List<Penalty> findByUser(User user) {
        return penaltyRepository.findByUser(user);
    }
}
