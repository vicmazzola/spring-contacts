package br.com.fiap.contacts.controller;


import br.com.fiap.contacts.dto.ContactExhibitionDto;
import br.com.fiap.contacts.dto.ContactRegisterDto;
import br.com.fiap.contacts.model.Contact;
import br.com.fiap.contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ContactExhibitionDto> listAllContacts(Pageable pageable) {
        return service.listAllContacts(pageable);
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
    public ContactExhibitionDto findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    //api/contacts?name=Victor
    @GetMapping(value = "/contacts", params = "name")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto findByNameStringQuery(@RequestParam String name) {
        return service.findByName(name);
    }


    @GetMapping("/contacts/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto searchById(@PathVariable Long id) {
        return service.searchById(id);
    }

    @GetMapping("/contacts/birthdays/{initialDate}/{finalDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactExhibitionDto> findByBirthDateBetween(
            @PathVariable LocalDate initialDate,
            @PathVariable LocalDate finalDate
    ) {
        return service.getBirthdayCelebrants(initialDate, finalDate);
    }

    //api/contacts?initialDate=1997-10-10&finalDate=1998-10-10
    @GetMapping(value = "/contacts", params = {"initialDate", "finalDate"})
    @ResponseStatus(HttpStatus.OK)
    public List<ContactExhibitionDto> findByBirthDateStringQuery(
            @RequestParam LocalDate initialDate,
            @RequestParam LocalDate finalDate
    ) {
        return service.getBirthdayCelebrants(initialDate, finalDate);
    }

    //api/contacts?email=victor@email.com
    @GetMapping(value = "/contacts", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto findByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }


}
