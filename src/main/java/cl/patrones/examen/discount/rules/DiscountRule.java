package cl.patrones.examen.discount.rules;

import cl.patrones.examen.productos.model.Producto;

/**
 * Regla de descuento: extensible y testeable.
 */
public interface DiscountRule {
    /**
     * Indica si la regla aplica al producto dado.
     */
    boolean supports(Producto producto);

    /**
     * Aplica el descuento y devuelve el nuevo precio.
     * Recibe el precio actual (puede ser el precio de lista o luego de aplicar reglas anteriores).
     */
    double apply(Producto producto, double precioActual);
}
