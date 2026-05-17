package cl.patrones.examen.discount.rules;

import org.springframework.stereotype.Component;

import cl.patrones.examen.productos.model.Producto;

/**
 * Regla por defecto: aplica un 10% si ninguna otra regla aplica.
 */
@Component
public class DefaultPercentRule implements DiscountRule {

    @Override
    public boolean supports(Producto producto, cl.patrones.examen.discount.DiscountContext context) {
        // Always supports; engine will use it only if no higher-priority rule applies
        return true;
    }

    @Override
    public double apply(Producto producto, double precioActual, cl.patrones.examen.discount.DiscountContext context) {
        return precioActual * 0.90;
    }
}
