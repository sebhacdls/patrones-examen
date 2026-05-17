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
        double precio = producto.getPrecioLista();

        // Buscar reglas específicas (excluyendo la default al final)
        for (DiscountRule r : rules) {
            if (r.getClass().getSimpleName().equals("DefaultPercentRule")) {
                continue;
            }
            if (r.supports(producto)) {
                return r.apply(producto, precio);
            }
        }

        // Si no hubo regla específica, buscar la default y aplicarla
        for (DiscountRule r : rules) {
            if (r.getClass().getSimpleName().equals("DefaultPercentRule")) {
                return r.apply(producto, precio);
            }
        }

        // Si no hay reglas registradas (caso extremo), retornar precio original
        return precio;
    }
}
