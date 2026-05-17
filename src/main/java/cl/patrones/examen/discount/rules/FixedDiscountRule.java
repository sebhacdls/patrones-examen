package cl.patrones.examen.discount.rules;

import org.springframework.stereotype.Component;

import cl.patrones.examen.productos.model.Producto;

/**
 * Regla que aplica un descuento fijo si el SKU contiene "5F" (ejemplo).
 */
@Component
public class FixedDiscountRule implements DiscountRule {

    @Override
    public boolean supports(Producto producto) {
        String sku = producto.getSku();
        return sku != null && sku.contains("5F");
    }

    @Override
    public double apply(Producto producto, double precioActual) {
        double result = precioActual - 5000.0; // ejemplo: 5.000 unidades
        return result < 0 ? 0 : result;
    }
}
