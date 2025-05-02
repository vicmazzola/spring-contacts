package br.com.fiap.contacts.dto;

/**
 * DTO for exposing user information.
 *
 * <p>This record represents the user data that can be shared externally,
 * such as user ID, name, and email. Sensitive information like passwords
 * are intentionally excluded.</p>
 *
 * <p>Fields:</p>
 * <ul>
 *   <li>{@code userId}: Unique identifier of the user.</li>
 *   <li>{@code name}: Name of the user.</li>
 *   <li>{@code email}: Email address of the user.</li>
 * </ul>
 */
public record UserExhibitionDto(
        Long userId,
        String name,
        String email

) {
}
