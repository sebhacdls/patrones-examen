package cl.patrones.examen.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    @GetMapping("/admin/toggle-employee")
    public String toggleEmployee(HttpServletRequest request, RedirectAttributes ra) {
        HttpSession session = request.getSession();
        Boolean current = (Boolean) session.getAttribute("employee");
        boolean next = current == null ? true : !current;
        session.setAttribute("employee", next);

        String referer = request.getHeader("Referer");
        if (referer == null) referer = "/";
        return "redirect:" + referer;
    }

    @GetMapping("/admin/set-day")
    public String setDay(HttpServletRequest request, @org.springframework.web.bind.annotation.RequestParam(required = false) String day) {
        HttpSession session = request.getSession();
        if (day != null) {
            session.setAttribute("discountDay", day);
        } else {
            session.removeAttribute("discountDay");
        }
        String referer = request.getHeader("Referer");
        if (referer == null) referer = "/";
        return "redirect:" + referer;
    }
}
