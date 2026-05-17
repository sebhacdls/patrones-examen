package cl.patrones.examen.discount.strategy;

public class FixedDiscount implements DiscountStrategy {

    private final double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double apply(double precioLista) {
        double result = precioLista - amount;
        return result < 0 ? 0 : result;
    }
}
