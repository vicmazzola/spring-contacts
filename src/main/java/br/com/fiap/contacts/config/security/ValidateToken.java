package br.com.fiap.contacts.config.security;

import br.com.fiap.contacts.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT validation filter.
 *
 * <p>Intercepts incoming HTTP requests to extract and validate JWT tokens.
 * If the token is valid, sets the authentication in the security context.</p>
 */
@Component
public class ValidateToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Constructor for dependency injection of TokenService.
     *
     * @param tokenService the token service to validate JWT tokens
     */
    public ValidateToken(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Filters each HTTP request to validate the JWT token.
     *
     * <p>If a valid token is found, sets the authenticated user in the security context.</p>
     *
     * @param request     incoming HTTP request
     * @param response    HTTP response
     * @param filterChain filter chain to continue the request processing
     * @throws ServletException if a servlet error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = "";

        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            token = null;
        } else {
            token = authorizationHeader.replace("Bearer", "").trim();
            String login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(login);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);

    }
}
