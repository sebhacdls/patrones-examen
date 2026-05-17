package cl.patrones.examen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.patrones.examen.productos.service.ProductoServiceImpl;
import cl.patrones.examen.discount.DiscountEngine;

@Configuration
public class Productor {

	@Bean
	ProductoServiceImpl productoServiceImpl(DiscountEngine discountEngine) {
		return new ProductoServiceImpl(discountEngine);
	}
}
