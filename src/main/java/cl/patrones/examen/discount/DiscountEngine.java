package cl.patrones.examen.discount;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.patrones.examen.discount.rules.DiscountRule;
import cl.patrones.examen.productos.model.Producto;

/**
 * Motor de descuentos: aplica reglas de forma extensible.
 *
 * Diseño:
 * - Inyecta todas las reglas (`DiscountRule`) registradas como beans.
 * - Para cada producto, busca la primera regla que soporte el producto y la aplica.
 * - Si ninguna regla específica aplica, usa la regla por defecto (la última en la lista,
 *   que típicamente devolverá un 10%).
 */
@Service
public class DiscountEngine {

    private final List<DiscountRule> rules;

    public DiscountEngine(List<DiscountRule> rules) {
        this.rules = rules;
    }

    public double calculateFinalPrice(Producto producto) {
        return calculateFinalPrice(producto, DiscountContext.defaultContext(java.time.DayOfWeek.from(java.time.LocalDate.now())));
    }

    public double calculateFinalPrice(Producto producto, DiscountContext context) {
        double precio = producto.getPrecioLista();
        // Primero evaluar reglas específicas (excluyendo la DefaultPercentRule)
        double bestSpecificPrice = precio;
        int bestPriority = Integer.MIN_VALUE;
        boolean anySpecific = false;
        for (DiscountRule r : rules) {
            if (r.getClass().getSimpleName().equals("DefaultPercentRule")) continue;
            if (r.supports(producto, context)) {
                double candidate = r.apply(producto, precio, context);
                int priority = r.getPriority();
                if (!anySpecific || priority > bestPriority || (priority == bestPriority && candidate < bestSpecificPrice)) {
                    bestSpecificPrice = candidate;
                    bestPriority = priority;
                    anySpecific = true;
                }
            }
        }

        if (anySpecific) {
            return bestSpecificPrice;
        }

        // Si no hay reglas específicas aplicables, aplicar la regla por defecto si existe
        for (DiscountRule r : rules) {
            if (r.getClass().getSimpleName().equals("DefaultPercentRule")) {
                return r.apply(producto, precio, context);
            }
        }

        // Ninguna regla aplica: retornar precio original
        return precio;
    }
}
