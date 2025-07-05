package com.example.gestion_bibliotheque.service.logs.impl;

import com.example.gestion_bibliotheque.entity.logs.ActivityLog;
import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.repository.logs.LogRepository;
import com.example.gestion_bibliotheque.service.logs.ActivityLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {

    private final LogRepository logRepository;

    public ActivityLogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void log(User user, String actionType, String message, LocalDateTime timestamp) {
        ActivityLog log = new ActivityLog(user, actionType, message, timestamp);
        logRepository.save(log);
    }
}

