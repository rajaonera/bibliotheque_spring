package com.example.gestion_bibliotheque.service.logs;

import com.example.gestion_bibliotheque.entity.user.User;

import java.time.LocalDateTime;

public interface ActivityLogService {
    void log(User user, String actionType, String message, LocalDateTime timestamp);
}
