package cl.patrones.examen.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cl.patrones.examen.discount.rules.EmployeeDiscountRule;
import cl.patrones.examen.productos.model.Producto;

class EmployeeDiscountRuleTest {

    private final EmployeeDiscountRule rule = new EmployeeDiscountRule();

    @Test
    void supportsOnlyForEmployeeContext() {
        Producto producto = new Producto("Taladro", "img", "T-1", 100.0);

        DiscountContext employeeCtx = new DiscountContext(true, java.time.DayOfWeek.MONDAY);
        assertTrue(rule.supports(producto, employeeCtx));

        DiscountContext nonEmployeeCtx = new DiscountContext(false, java.time.DayOfWeek.MONDAY);
        assertFalse(rule.supports(producto, nonEmployeeCtx));
    }

    @Test
    void appliesFivePercentDiscount() {
        Producto producto = new Producto("Taladro", "img", "T-1", 100.0);
        DiscountContext ctx = new DiscountContext(true, java.time.DayOfWeek.MONDAY);

        assertEquals(95.0, rule.apply(producto, producto.getPrecioLista(), ctx), 0.001);
    }
}
