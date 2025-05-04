package br.com.fiap.contacts.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security.
 *
 * <p>Configures Spring Security settings such as stateless session management, HTTP authorization rules, and registers a JWT token validation filter.</p>
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private ValidateToken validateToken;

    /**
     * Configures the security filter chain for the application.
     *
     * <p>Disables CSRF protection, sets the session management to stateless,
     * adds a JWT token validation filter, and defines authorization rules:
     * <ul>
     *     <li>Allows all POST requests to /auth/register and /auth/login without authentication.</li>
     *     <li>Allows all GET requests to /api/contacts without authentication.</li>
     *     <li>Restricts POST requests to /api/contacts to users with ADMIN role.</li>
     *     <li>Requires authentication for all other requests.</li>
     * </ul>
     * </p>
     *
     * @param httpSecurity the {@link HttpSecurity} to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if a security configuration error occurs
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contacts").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/api/contacts").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/contacts").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contacts").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        validateToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }


    /**
     * Exposes the {@link AuthenticationManager} bean to be used for authentication purposes.
     *
     * <p>Retrieves the {@link AuthenticationManager} from the {@link AuthenticationConfiguration},
     * allowing it to be injected where required (e.g. controllers or services).</p>
     *
     * @param authenticationConfiguration the {@link AuthenticationConfiguration} provided by Spring
     * @return the configured {@link AuthenticationManager}
     * @throws Exception if an error occurs while retrieving the manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Exposes the {@link PasswordEncoder} bean to encode and verify passwords.
     *
     * @return a {@link PasswordEncoder} using BCrypt hashing algorithm
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
