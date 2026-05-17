package cl.patrones.examen.discount;

import java.time.DayOfWeek;

/**
 * Contexto de cálculo de descuento: permite indicar si el cliente es empleado
 * y/o forzar el día de la semana (útil para tests).
 */
public class DiscountContext {
    private final boolean employee;
    private final DayOfWeek dayOfWeek;

    public DiscountContext(boolean employee, DayOfWeek dayOfWeek) {
        this.employee = employee;
        this.dayOfWeek = dayOfWeek;
    }

    public boolean isEmployee() {
        return employee;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public static DiscountContext defaultContext(java.time.DayOfWeek systemDay) {
        return new DiscountContext(false, systemDay);
    }
}
