package com.example.gestion_bibliotheque.service.loan;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.gestion_bibliotheque.entity.loan.LoanPolicy;
import com.example.gestion_bibliotheque.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {

    public static LocalDate calculateDueDate(LocalDate start_due , LoanPolicy policy) throws BusinessException {

        return start_due.plusDays(policy.getLoanDurationDays());
    }


}
