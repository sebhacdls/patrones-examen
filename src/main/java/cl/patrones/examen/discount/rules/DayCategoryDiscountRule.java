package cl.patrones.examen.discount.rules;

import java.time.DayOfWeek;
import java.util.Map;

import org.springframework.stereotype.Component;

import cl.patrones.examen.productos.model.Producto;
import cl.patrones.examen.discount.DiscountContext;

/**
 * Regla que aplica descuentos por día y categoría:
 * - Lunes: 6% compresores
 * - Martes: 8% esmeriles
 * - Miércoles: 10% taladros
 */
@Component
public class DayCategoryDiscountRule implements DiscountRule {

    private final DayOfWeekProvider dayProvider;

    // category -> lowercase keywords mapping
    private final Map<String, String> categoryKeywords = Map.of(
            "COMPRESOR", "compresor",
            "ESMERIL", "esmeril",
            "TALADRO", "taladro"
    );

    public DayCategoryDiscountRule(DayOfWeekProvider dayProvider) {
        this.dayProvider = dayProvider;
    }

    @Override
    public boolean supports(Producto producto, DiscountContext context) {
        // This rule supports if the product name matches known categories and the day matches
        String name = producto.getNombre();
        if (name == null) return false;
        DayOfWeek day = context != null && context.getDayOfWeek() != null ? context.getDayOfWeek() : dayProvider.currentDay();

        if (day == DayOfWeek.MONDAY && name.toLowerCase().contains("compresor")) return true;
        if (day == DayOfWeek.TUESDAY && name.toLowerCase().contains("esmeril")) return true;
        if (day == DayOfWeek.WEDNESDAY && name.toLowerCase().contains("taladro")) return true;
        return false;
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public double apply(Producto producto, double precioActual, DiscountContext context) {
        if (!supports(producto, context)) {
            return precioActual;
        }

        DayOfWeek day = context != null && context.getDayOfWeek() != null ? context.getDayOfWeek() : dayProvider.currentDay();
        if (day == DayOfWeek.MONDAY) return precioActual * 0.94; // 6%
        if (day == DayOfWeek.TUESDAY) return precioActual * 0.92; // 8%
        if (day == DayOfWeek.WEDNESDAY) return precioActual * 0.90; // 10%
        return precioActual;
    }
}
