package cl.patrones.examen.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.patrones.examen.discount.rules.DefaultPercentRule;
import cl.patrones.examen.discount.rules.DiscountRule;
import cl.patrones.examen.productos.model.Producto;

@ExtendWith(MockitoExtension.class)
class DiscountEngineUnitTest {

    @Mock
    private DiscountRule highPriorityRule;

    @Mock
    private DiscountRule lowPriorityRule;

    @Mock
    private DiscountRule samePriorityCheapRule;

    @Mock
    private DiscountRule samePriorityExpensiveRule;

    @Test
    void highestPriorityRuleWinsEvenIfPriceHigher() {
        Producto producto = new Producto("X", "img", "SKU-TEST", 100.0);
        DiscountContext ctx = new DiscountContext(false, java.time.DayOfWeek.MONDAY);

        when(highPriorityRule.supports(producto, ctx)).thenReturn(true);
        when(highPriorityRule.getPriority()).thenReturn(100);
        when(highPriorityRule.apply(producto, 100.0, ctx)).thenReturn(95.0);

        when(lowPriorityRule.supports(producto, ctx)).thenReturn(true);
        when(lowPriorityRule.getPriority()).thenReturn(10);
        when(lowPriorityRule.apply(producto, 100.0, ctx)).thenReturn(90.0);

        DiscountEngine engine = new DiscountEngine(List.of(highPriorityRule, lowPriorityRule));
        double finalPrice = engine.calculateFinalPrice(producto, ctx);

        assertEquals(95.0, finalPrice, 0.001);
    }

    @Test
    void samePriorityChoosesLowestPrice() {
        Producto producto = new Producto("X", "img", "SKU-TEST", 200.0);
        DiscountContext ctx = new DiscountContext(false, java.time.DayOfWeek.TUESDAY);

        when(samePriorityCheapRule.supports(producto, ctx)).thenReturn(true);
        when(samePriorityCheapRule.getPriority()).thenReturn(50);
        when(samePriorityCheapRule.apply(producto, 200.0, ctx)).thenReturn(160.0);

        when(samePriorityExpensiveRule.supports(producto, ctx)).thenReturn(true);
        when(samePriorityExpensiveRule.getPriority()).thenReturn(50);
        when(samePriorityExpensiveRule.apply(producto, 200.0, ctx)).thenReturn(170.0);

        DiscountEngine engine = new DiscountEngine(List.of(samePriorityExpensiveRule, samePriorityCheapRule));
        double finalPrice = engine.calculateFinalPrice(producto, ctx);

        assertEquals(160.0, finalPrice, 0.001);
    }

    @Test
    void defaultRuleAppliedWhenNoOtherRuleSupports() {
        Producto producto = new Producto("X", "img", "SKU-TEST", 100.0);
        DiscountContext ctx = new DiscountContext(false, java.time.DayOfWeek.WEDNESDAY);

        DefaultPercentRule defaultRule = new DefaultPercentRule();
        DiscountEngine engine = new DiscountEngine(List.of(defaultRule));

        double finalPrice = engine.calculateFinalPrice(producto, ctx);

        assertEquals(90.0, finalPrice, 0.001);
    }

    @Test
    void noSupportingRulesReturnsListPriceWhenNoDefaultRule() {
        Producto producto = new Producto("X", "img", "SKU-TEST", 100.0);
        DiscountContext ctx = new DiscountContext(false, java.time.DayOfWeek.WEDNESDAY);

        DiscountEngine engine = new DiscountEngine(List.of(highPriorityRule));
        when(highPriorityRule.supports(producto, ctx)).thenReturn(false);

        double finalPrice = engine.calculateFinalPrice(producto, ctx);

        assertEquals(100.0, finalPrice, 0.001);
    }
}
