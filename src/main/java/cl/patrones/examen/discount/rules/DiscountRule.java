package cl.patrones.examen.discount.rules;

import cl.patrones.examen.productos.model.Producto;
import cl.patrones.examen.discount.DiscountContext;

/**
 * Regla de descuento: extensible y testeable.
 */
public interface DiscountRule {
    /**
     * Indica si la regla aplica al producto dado.
     */
    boolean supports(Producto producto, DiscountContext context);

    /**
     * Aplica el descuento y devuelve el nuevo precio.
     * Recibe el precio actual (puede ser el precio de lista o luego de aplicar reglas anteriores).
     */
    double apply(Producto producto, double precioActual, DiscountContext context);

    /**
     * Prioriza reglas específicas cuando varias son aplicables.
     * Las reglas con prioridad mayor ganan sobre otras reglas específicas.
     */
    default int getPriority() {
        return 0;
    }
}
