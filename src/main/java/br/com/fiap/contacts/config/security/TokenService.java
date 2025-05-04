package br.com.fiap.contacts.config.security;

import br.com.fiap.contacts.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Service responsible for generating and validating JWT tokens.
 *
 * <p>Generates tokens with expiration and validates received tokens,
 * returning the subject (user email) if valid.</p>
 */
@Service
public class TokenService {

    @Value("${my.secret.key}")
    private String secretKey;


    /**
     * Generates a JWT token for the given user.
     *
     * @param user the user for whom the token will be generated
     * @return the generated JWT token as a String
     * @throws RuntimeException if token generation fails
     */
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT
                    .create()
                    .withIssuer("contacts")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException error) {
            throw new RuntimeException("Was not possible to generate the token!");
        }
    }

    /**
     * Validates a JWT token and retrieves the subject (user email).
     *
     * @param token the JWT token to validate
     * @return the subject (user email) if valid, or an empty string if invalid
     */
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("contacts")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException error) {
            return "";

        }
    }

    /**
     * Generates the expiration date for the JWT token.
     *
     * @return an {@link Instant} representing the expiration date and time
     */
    public Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
