package cl.patrones.examen.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cl.patrones.examen.discount.rules.DayCategoryDiscountRule;
import cl.patrones.examen.discount.rules.DayOfWeekProvider;
import cl.patrones.examen.productos.model.Producto;

class DayCategoryDiscountRuleTest {

    private DayCategoryDiscountRule rule;

    @BeforeEach
    void setUp() {
        rule = new DayCategoryDiscountRule(() -> DayOfWeek.MONDAY);
    }

    @Test
    void mondayCompresorIsSupported() {
        Producto producto = new Producto("Compresor Turbo", "img", "C-1", 1000.0);
        DiscountContext ctx = new DiscountContext(false, DayOfWeek.MONDAY);

        assertTrue(rule.supports(producto, ctx));
        assertEquals(940.0, rule.apply(producto, producto.getPrecioLista(), ctx), 0.001);
    }

    @Test
    void tuesdayEsmerilIsSupported() {
        Producto producto = new Producto("Esmeril angular", "img", "E-1", 100.0);
        DiscountContext ctx = new DiscountContext(false, DayOfWeek.TUESDAY);

        assertTrue(rule.supports(producto, ctx));
        assertEquals(92.0, rule.apply(producto, producto.getPrecioLista(), ctx), 0.001);
    }

    @Test
    void wednesdayTaladroIsSupported() {
        Producto producto = new Producto("Taladro percutor", "img", "T-1", 100.0);
        DiscountContext ctx = new DiscountContext(false, DayOfWeek.WEDNESDAY);

        assertTrue(rule.supports(producto, ctx));
        assertEquals(90.0, rule.apply(producto, producto.getPrecioLista(), ctx), 0.001);
    }

    @Test
    void unsupportedProductReturnsFalse() {
        Producto producto = new Producto("Martillo", "img", "H-1", 100.0);
        DiscountContext ctx = new DiscountContext(false, DayOfWeek.MONDAY);

        assertFalse(rule.supports(producto, ctx));
        assertEquals(100.0, rule.apply(producto, producto.getPrecioLista(), ctx), 0.001);
    }
}
