package br.com.fiap.contacts.repository;


import br.com.fiap.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    public Contact findByName(String name);

    public List<Contact> findByBirthDateBetween(LocalDate from, LocalDate to);

}
