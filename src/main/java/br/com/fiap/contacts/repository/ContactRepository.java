package br.com.fiap.contacts.repository;


import br.com.fiap.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    public Optional<Contact> findByName(String name);

    public List<Contact> findByBirthDateBetween(LocalDate from, LocalDate to);

}
