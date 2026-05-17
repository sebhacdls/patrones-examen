package cl.patrones.examen.productos.service;

import java.util.List;
import cl.patrones.examen.productos.model.Producto;
import cl.patrones.examen.discount.DiscountContext;

public interface ProductoService {
    List<Producto> getProductos();
    List<Producto> getProductos(DiscountContext context);
}
