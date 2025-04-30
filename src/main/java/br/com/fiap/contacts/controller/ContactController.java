package br.com.fiap.contacts.controller;


import br.com.fiap.contacts.dto.ContactExhibitionDto;
import br.com.fiap.contacts.dto.ContactRegisterDto;
import br.com.fiap.contacts.model.Contact;
import br.com.fiap.contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactExhibitionDto save(@RequestBody @Valid ContactRegisterDto contactRegisterDto) {
        return service.save(contactRegisterDto);
    }

    @GetMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> listAllContacts() {
        return service.listAllContacts();
    }

    @DeleteMapping("/contacts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public Contact update(@RequestBody Contact contact) {
        return service.update(contact);
    }

    @GetMapping("/contacts/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Contact findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/contacts/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto searchById(@PathVariable Long id) {
        return service.searchById(id);
    }

    @GetMapping("/contacts/birthdays/{from}/{to}")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> findByBirthDateBetween(
            @PathVariable LocalDate from,
            @PathVariable LocalDate to
    ) {
        return service.getBirthdayCelebrants(from, to);
    }

}
