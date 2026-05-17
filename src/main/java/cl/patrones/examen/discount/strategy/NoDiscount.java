package cl.patrones.examen.discount.strategy;

public class NoDiscount implements DiscountStrategy {

    @Override
    public double apply(double precioLista) {
        return precioLista;
    }
}
