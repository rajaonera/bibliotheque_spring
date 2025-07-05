package com.example.gestion_bibliotheque.controller.loan;

import com.example.gestion_bibliotheque.dto.loan.PenaltyDTO;
import com.example.gestion_bibliotheque.entity.loan.Penalty;
import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.exception.BusinessException;
import com.example.gestion_bibliotheque.service.loan.impl.PenaltyServiceImpl;
import com.example.gestion_bibliotheque.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/penalties")
public class PenaltyController {

    private final PenaltyServiceImpl penaltyService;
    private final UserService userService;

    public PenaltyController(PenaltyServiceImpl penaltyService, UserService userService) {
        this.penaltyService = penaltyService;
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<List<PenaltyDTO>> getUserPenalties(@RequestParam Long userId) throws BusinessException {
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isEmpty()) {
            throw new BusinessException("User not found");
        }

        List<Penalty> penalties = penaltyService.findByUser(userOpt.get());
        List<PenaltyDTO> dtoList = penalties.stream()
                .map(PenaltyDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}

