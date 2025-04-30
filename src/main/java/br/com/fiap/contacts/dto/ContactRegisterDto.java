package br.com.fiap.contacts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * DTO for registering a new contact.
 *
 * <p>This record encapsulates the necessary information to create a new contact,
 * including name, email, password, and birth date. It ensures that all required
 * fields are present and valid through the use of validation annotations.</p>
 *
 * <p>Validation constraints applied:</p>
 * <ul>
 *   <li>{@code name}: must not be blank.</li>
 *   <li>{@code email}: must not be blank and must follow a valid email format.</li>
 *   <li>{@code password}: must not be blank and must be between 6 and 10 characters long.</li>
 *   <li>{@code birthDate}: must not be null.</li>
 * </ul>
 */
public record ContactRegisterDto(
        Long id,

        @NotBlank(message = "Contact name is mandatory!")
        String name,

        @NotBlank(message = "E-mail is mandatory!")
        @Email(message = "Invalid e-mail format")
        String email,

        @NotBlank(message = "Password is mandatory!")
        @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters long")
        String password,

        @NotNull(message = "Birth date is mandatory!")
        LocalDate birthDate) {

}
