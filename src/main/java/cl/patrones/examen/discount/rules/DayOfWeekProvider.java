package cl.patrones.examen.discount.rules;

import java.time.DayOfWeek;

public interface DayOfWeekProvider {
    DayOfWeek currentDay();
}
