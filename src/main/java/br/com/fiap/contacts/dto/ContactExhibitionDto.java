package br.com.fiap.contacts.dto;

import br.com.fiap.contacts.model.Contact;

import java.time.LocalDate;


public record ContactExhibitionDto(
        Long id,
        String name,
        String email,
        LocalDate birthDate) {
    /**
     * Constructs a ContactExhibitionDto from a Contact entity.
     *
     * @param contact the contact entity
     */
    public ContactExhibitionDto(Contact contact) {
        this(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getBirthDate());
    }

}
