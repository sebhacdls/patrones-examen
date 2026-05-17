package cl.patrones.examen.discount.rules;

import org.springframework.stereotype.Component;

import cl.patrones.examen.productos.model.Producto;

/**
 * Regla que aplica un porcentaje de descuento cuando el SKU contiene un marcador como "10P".
 */
@Component
public class PercentDiscountRule implements DiscountRule {

    @Override
    public boolean supports(Producto producto) {
        String sku = producto.getSku();
        return sku != null && sku.matches(".*\\d+P.*");
    }

    @Override
    public double apply(Producto producto, double precioActual) {
        String sku = producto.getSku();
        try {
            java.util.regex.Matcher m = java.util.regex.Pattern.compile("(\\d+)P").matcher(sku);
            if (m.find()) {
                String num = m.group(1);
                double rate = Double.parseDouble(num) / 100.0;
                return precioActual * (1 - rate);
            }
            return precioActual;
        } catch (Exception e) {
            return precioActual;
        }
    }
}
