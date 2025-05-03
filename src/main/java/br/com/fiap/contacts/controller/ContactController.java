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

/**
 * Controller responsible for handling Contact-related HTTP requests.
 *
 * <p>Provides endpoints for creating, retrieving, updating, deleting and searching contacts.
 * Supports multiple ways of searching (by ID, name, email, and birthday range).</p>
 */
@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private ContactService service;

    /**
     * Creates a new contact.
     *
     * @param contactRegisterDto the contact data to register
     * @return the created contact as ContactExhibitionDto
     */
    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactExhibitionDto save(@RequestBody @Valid ContactRegisterDto contactRegisterDto) {
        return service.save(contactRegisterDto);
    }

    /**
     * Lists all contacts with pagination.
     *
     * @param pageable pagination information
     * @return paginated list of contacts
     */
    @GetMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContactExhibitionDto> listAllContacts(Pageable pageable) {
        return service.listAllContacts(pageable);
    }

    /**
     * Deletes a contact by ID.
     *
     * @param id the contact ID
     */
    @DeleteMapping("/contacts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    /**
     * Updates an existing contact.
     *
     * @param contact the contact with updated data
     * @return the updated contact
     */
    @PutMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public Contact update(@RequestBody Contact contact) {
        return service.update(contact);
    }

    /**
     * Finds a contact by name using a path variable.
     *
     * @param name the contact name
     * @return the contact found
     */
    @GetMapping("/contacts/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    /**
     * Finds a contact by name using a query parameter.
     * Example: /api/contacts?name=Victor
     *
     * @param name the contact name
     * @return the contact found
     */
    @GetMapping(value = "/contacts", params = "name")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto findByNameStringQuery(@RequestParam String name) {
        return service.findByName(name);
    }

    /**
     * Finds a contact by ID.
     *
     * @param id the contact ID
     * @return the contact found
     */
    @GetMapping("/contacts/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto searchById(@PathVariable Long id) {
        return service.searchById(id);
    }

    /**
     * Finds contacts with birthdays between two dates using path variables.
     *
     * @param initialDate the start date
     * @param finalDate   the end date
     * @return list of contacts within the birthday range
     */
    @GetMapping("/contacts/birthdays/{initialDate}/{finalDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContactExhibitionDto> findByBirthDateBetween(
            @PathVariable LocalDate initialDate,
            @PathVariable LocalDate finalDate
    ) {
        return service.getBirthdayCelebrants(initialDate, finalDate);
    }

    /**
     * Finds contacts with birthdays between two dates using query parameters.
     * Example: /api/contacts?initialDate=1997-10-10&finalDate=1998-10-10
     *
     * @param initialDate the start date
     * @param finalDate   the end date
     * @return list of contacts within the birthday range
     */
    @GetMapping(value = "/contacts", params = {"initialDate", "finalDate"})
    @ResponseStatus(HttpStatus.OK)
    public List<ContactExhibitionDto> findByBirthDateStringQuery(
            @RequestParam LocalDate initialDate,
            @RequestParam LocalDate finalDate
    ) {
        return service.getBirthdayCelebrants(initialDate, finalDate);
    }

    /**
     * Finds a contact by email.
     * Example: /api/contacts?email=victor@email.com
     *
     * @param email the contact email
     * @return the contact found
     */
    @GetMapping(value = "/contacts", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public ContactExhibitionDto findByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }
}
