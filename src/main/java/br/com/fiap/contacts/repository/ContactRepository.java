package br.com.fiap.contacts.repository;


import br.com.fiap.contacts.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    @Query("SELECT c FROM Contact c WHERE c.name = :name")
    Optional<Contact> findByName(@Param("name") String name);

    Optional<Contact> findByEmail(String email);

    @Query("SELECT c FROM Contact c WHERE c.birthDate BETWEEN :initialDate AND :finalDate")
    List<Contact> findByBirthDateBetween(
            @Param("initialDate") LocalDate initialDate,
            @Param("finalDate") LocalDate finalDate);

}
