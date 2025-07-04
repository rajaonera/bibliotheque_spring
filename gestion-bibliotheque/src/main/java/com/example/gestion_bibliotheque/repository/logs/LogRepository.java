package com.example.gestion_bibliotheque.repository.logs;

import com.example.gestion_bibliotheque.entity.logs.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByUserId(Long userId);

    List<ActivityLog> findByActionType(String actionType);
}
