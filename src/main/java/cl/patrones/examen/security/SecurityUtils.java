package cl.patrones.examen.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utilidad de Spring Security para obtener el usuario autenticado.
 */
public final class SecurityUtils {

    private SecurityUtils() {
        // Utilidad estática
    }

    public static UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        }

        return null;
    }

    public static String getCurrentUsername() {
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails != null) {
            return userDetails.getUsername();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return authentication.getPrincipal().toString();
        }

        return null;
    }

    public static boolean currentUserHasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities() == null) {
            return false;
        }

        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role::equals);
    }

    public static boolean isCurrentUserEmpleado() {
        return currentUserHasRole("ROLE_EMPLEADO");
    }
}
