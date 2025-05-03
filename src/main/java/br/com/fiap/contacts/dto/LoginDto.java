package br.com.fiap.contacts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "E-mail is required!")
        @Email(message = "E-mail is not valid!")
        String email,

        @NotBlank(message = "Password is required!")
        @Size(min = 6, max = 20, message = "Password must have between 6 and 20 characters ")
        String password
) {


}
