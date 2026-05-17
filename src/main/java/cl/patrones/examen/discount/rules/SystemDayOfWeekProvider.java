package cl.patrones.examen.discount.rules;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class SystemDayOfWeekProvider implements DayOfWeekProvider {
    @Override
    public DayOfWeek currentDay() {
        return LocalDate.now().getDayOfWeek();
    }
}
