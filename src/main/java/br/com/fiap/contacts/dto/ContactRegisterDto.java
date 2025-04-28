package br.com.fiap.contacts.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record ContactRegisterDto(
        Long id,
        String name,
        String email,
        String password,
        LocalDate birthDate) {

}
