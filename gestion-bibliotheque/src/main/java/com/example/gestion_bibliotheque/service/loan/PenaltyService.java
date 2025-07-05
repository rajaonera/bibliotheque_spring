package com.example.gestion_bibliotheque.service.loan;

import com.example.gestion_bibliotheque.entity.loan.Penalty;
import com.example.gestion_bibliotheque.entity.user.User;

import java.util.List;

public interface PenaltyService {
    List<Penalty> findByUser(User user);
}
