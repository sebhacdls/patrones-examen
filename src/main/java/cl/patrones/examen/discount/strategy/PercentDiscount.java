package cl.patrones.examen.discount.strategy;

public class PercentDiscount implements DiscountStrategy {

    private final double rate;

    public PercentDiscount(double rate) {
        this.rate = rate;
    }

    @Override
    public double apply(double precioLista) {
        return precioLista * (1 - rate);
    }
}
