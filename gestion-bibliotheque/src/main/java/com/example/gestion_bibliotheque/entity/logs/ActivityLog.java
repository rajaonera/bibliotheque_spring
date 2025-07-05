package com.example.gestion_bibliotheque.entity.logs;

import com.example.gestion_bibliotheque.entity.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "action_type")
    private String actionType;

    private String description;

    private LocalDateTime timestamp;

    // Constructeur vide
    public ActivityLog() {
    }

    // Constructeur complet
    public ActivityLog( User user, String actionType, String description, LocalDateTime timestamp) {
        this.user = user;
        this.actionType = actionType;
        this.description = description;
        this.timestamp = timestamp;
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
