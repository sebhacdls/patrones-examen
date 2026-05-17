package cl.patrones.examen.discount.rules;

import org.springframework.stereotype.Component;

import cl.patrones.examen.discount.DiscountContext;
import cl.patrones.examen.productos.model.Producto;

/**
 * Regla que aplica 5% fijo si el contexto indica que el cliente es empleado.
 */
@Component
public class EmployeeDiscountRule implements DiscountRule {

    @Override
    public boolean supports(Producto producto, DiscountContext context) {
        return context != null && context.isEmployee();
    }

    @Override
    public int getPriority() {
        return 50;
    }

    @Override
    public double apply(Producto producto, double precioActual, DiscountContext context) {
        return precioActual * 0.95; // 5% employee discount
    }
}
