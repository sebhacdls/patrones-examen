package cl.patrones.examen.productos.service;

import java.util.ArrayList;
import java.util.List;

import cl.patrones.examen.productos.model.Producto;
import cl.patrones.examen.discount.DiscountEngine;

public class ProductoServiceImpl implements ProductoService {

    private final DiscountEngine discountEngine;

    public ProductoServiceImpl(DiscountEngine discountEngine) {
        this.discountEngine = discountEngine;
    }

    @Override
    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();

        Producto p1 = new Producto("Compresor 2HP 8 bar L", "compresor-2hp.jpg", "COMP-2HP-8L", 250000.0);
        Producto p2 = new Producto("Compresor Indura Huracan 1520 2HP 8 bar 50L", "compresor-indura-huracan.jpg", "IND-1520-50L-10P", 320000.0);
        Producto p3 = new Producto("Esmeril angular 4 1/2\" 850W Makita", "esmeril-angular-makita.jpg", "ESM-MAK-4.5-850W", 45000.0);
        Producto p4 = new Producto("Esmeril angular 115MM 60W Erboc", "esmeril-angular-erboc.jpg", "ESM-ERB-115-60W-5F", 35000.0);
        Producto p5 = new Producto("Taladro percutor 13MM 900W Erboc", "taladro-percutor-erboc.jpg", "TAL-ERB-13-900W", 60000.0);
        Producto p6 = new Producto("Taladro percutor 10MM 550W Black&decker", "taladro-percutor-bd.jpg", "TAL-BD-10-550W", 45000.0);

        productos.add(applyDiscount(p1));
        productos.add(applyDiscount(p2));
        productos.add(applyDiscount(p3));
        productos.add(applyDiscount(p4));
        productos.add(applyDiscount(p5));
        productos.add(applyDiscount(p6));

        return productos;
    }

    private Producto applyDiscount(Producto p) {
        double precioConDescuento = discountEngine.calculateFinalPrice(p);
        p.setPrecioFinal(precioConDescuento);
        return p;
    }
}
