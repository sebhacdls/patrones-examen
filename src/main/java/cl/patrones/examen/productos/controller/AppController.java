package cl.patrones.examen.productos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import cl.patrones.examen.productos.service.ProductoService;
import cl.patrones.examen.discount.DiscountContext;

@Controller
public class AppController {
	
	private ProductoService productoService;
	
	public AppController(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}

	@GetMapping("/")
	String inicio(Model model, HttpSession session) {
		// Build discount context from session (employee flag and optional discountDay)
		boolean isEmployee = false;
		java.time.DayOfWeek sessionDay = null;

		if (session != null) {
			Object emp = session.getAttribute("employee");
			if (emp instanceof Boolean) isEmployee = (Boolean) emp;
			Object d = session.getAttribute("discountDay");
			if (d instanceof String) {
				try {
					sessionDay = java.time.DayOfWeek.valueOf(((String) d).toUpperCase());
				} catch (Exception ex) {
					sessionDay = null;
				}
			}
		}

		java.time.DayOfWeek dayToUse = sessionDay == null ? java.time.LocalDate.now().getDayOfWeek() : sessionDay;
		DiscountContext ctx = new DiscountContext(isEmployee, dayToUse);

		var productos = productoService.getProductos(ctx);
		model.addAttribute("productos", productos);
		return "inicio";
	}

}
