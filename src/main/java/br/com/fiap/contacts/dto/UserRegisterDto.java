package br.com.fiap.contacts.dto;

import br.com.fiap.contacts.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;


/**
 * DTO for registering a new user.
 *
 * <p>This record encapsulates the data required to create a user account,
 * including name, email, and password. Validation constraints ensure the
 * integrity and correctness of the provided data.</p>
 *
 * <p>Validation constraints applied:</p>
 * <ul>
 *   <li>{@code name}: Must not be blank.</li>
 *   <li>{@code email}: Must not be blank and must follow a valid email format.</li>
 *   <li>{@code password}: Must not be blank and must be between 6 and 20 characters long.</li>
 * </ul>
 */
public record UserRegisterDto(
        Long userId,

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Email is required.")
        @Email(message = "Invalid email format.")
        String email,

        @NotBlank(message = "Password is required.")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters.")
        String password,

        UserRole role
) {
}
