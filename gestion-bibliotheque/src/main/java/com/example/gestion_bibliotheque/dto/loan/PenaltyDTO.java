package com.example.gestion_bibliotheque.dto.loan;
import com.example.gestion_bibliotheque.entity.loan.Penalty;

import java.time.LocalDate;

public class PenaltyDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int days;
    private String reason;
    private boolean active;

    // Constructeurs, getters, setters
    public static PenaltyDTO fromEntity(Penalty penalty) {
        PenaltyDTO dto = new PenaltyDTO();
        dto.setId(penalty.getId());
        dto.setStartDate(penalty.getStartDate());
        dto.setEndDate(penalty.getEndDate());
        dto.setDays(penalty.getDays());
        dto.setReason(penalty.getReason());
        dto.setActive(penalty.isActive());
        return dto;
    }
    public PenaltyDTO () {}

    public PenaltyDTO(LocalDate startDate, LocalDate endDate, int days, String reason, boolean active) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.reason = reason;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}

