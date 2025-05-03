package br.com.fiap.contacts.config.security;


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

/**
 * Configuration class for Spring Security.
 *
 * <p>Defines security settings including stateless session management,
 * CSRF disabling, and HTTP request authorization rules.</p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     *
     * <p>Disables CSRF protection, sets the session management to stateless,
     * and defines authorization rules:
     * <ul>
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
                        .requestMatchers(HttpMethod.GET, "/api/contacts").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/contacts").hasRole("ADMIN")
                        .anyRequest().authenticated()
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

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
