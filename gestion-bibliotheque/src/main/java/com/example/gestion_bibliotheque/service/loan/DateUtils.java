package com.example.gestion_bibliotheque.service.loan;

import java.time.LocalDate;

import com.example.gestion_bibliotheque.entity.loan.LoanPolicy;
import com.example.gestion_bibliotheque.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {

    public static LocalDate calculateDueDate(LocalDate start_due , LoanPolicy policy) throws BusinessException {

        LocalDate dueDate = start_due.plusDays(policy.getLoanDurationDays());

        // Décaler si jour férié ou week-end
//        while (isWeekendOrHoliday(dueDate,holidayRepository )) {
//            dueDate = dueDate.plusDays(1);
//        }

        return dueDate;
    }


//    public static boolean isWeekendOrHoliday(LocalDate date ,HolidayRepository holidayRepository) throws BusinessException {
//        DayOfWeek day = date.getDayOfWeek();
//        boolean isWeekend = (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
//        boolean isHoliday = holidayRepository.existsByDate(date);
//        return isWeekend || isHoliday;
//    }
}
