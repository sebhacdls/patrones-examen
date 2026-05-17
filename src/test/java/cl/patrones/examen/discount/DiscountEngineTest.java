package cl.patrones.examen.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.patrones.examen.productos.model.Producto;

@SpringBootTest
class DiscountEngineTest {

    @Autowired
    private DiscountEngine discountEngine;

    @Test
    void defaultPercentAppliedWhenNoSpecificRule() {
        Producto p = new Producto("X", "img", "SKU-UNKNOWN", 100.0);
        double finalPrice = discountEngine.calculateFinalPrice(p);
        assertEquals(90.0, finalPrice, 0.001);
    }

    @Test
    void percentRuleAppliedWhenSkuContainsMarker() {
        Producto p = new Producto("X", "img", "SKU-10P", 200.0);
        double finalPrice = discountEngine.calculateFinalPrice(p);
        assertEquals(180.0, finalPrice, 0.001);
    }

    @Test
    void fixedRuleAppliedWhenSkuContains5F() {
        Producto p = new Producto("X", "img", "SKU-5F", 10000.0);
        double finalPrice = discountEngine.calculateFinalPrice(p);
        assertEquals(5000.0, finalPrice, 0.001);
    }
}
