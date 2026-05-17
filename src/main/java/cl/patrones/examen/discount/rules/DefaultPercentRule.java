package cl.patrones.examen.discount.rules;

import org.springframework.stereotype.Component;

import cl.patrones.examen.productos.model.Producto;

/**
 * Regla por defecto: aplica un 10% si ninguna otra regla aplica.
 */
@Component
public class DefaultPercentRule implements DiscountRule {

    @Override
    public boolean supports(Producto producto) {
        // Esta regla es considerada por el engine sólo si no hay otras aplicables.
        return true;
    }

    @Override
    public double apply(Producto producto, double precioActual) {
        return precioActual * 0.90;
    }
}
