package stirling.software.SPDF.config.security.saml2;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SamlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // Redirect to Keycloak's Single Logout endpoint
        response.sendRedirect(
                "http://localhost:8080/auth/realms/saml-sso/protocol/saml"); // Adjust URL as
        // necessary
    }
}
