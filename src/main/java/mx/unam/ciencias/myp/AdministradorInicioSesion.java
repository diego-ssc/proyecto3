package mx.unam.ciencias.myp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Clase que administra los inicios de sesión exitosos
 * determinando el rol del usuario que accedió a la página.
 *
 */
@Component
public class AdministradorInicioSesion extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override public void onAuthenticationSuccess(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  Authentication authentication)
        throws ServletException, IOException {
        InformacionUsuario informacionUsuario =
            (InformacionUsuario) authentication.getPrincipal();
        String url = request.getContextPath();

        if (informacionUsuario.hasRole("administrador")) {
            //url = "/administrator";
        } else {
            url = "/user";
        }

        response.sendRedirect(url);
    }
}
