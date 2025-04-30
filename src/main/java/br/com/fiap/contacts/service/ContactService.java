package br.com.fiap.contacts.service;

import br.com.fiap.contacts.dto.ContactExhibitionDto;
import br.com.fiap.contacts.dto.ContactRegisterDto;
import br.com.fiap.contacts.exception.UserNotFindException;
import br.com.fiap.contacts.model.Contact;
import br.com.fiap.contacts.repository.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for business logic related to Contact entities.
 */
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Saves a contact to the database.
     *
     * @param contactRegisterDto the contact to save
     * @return the saved contact
     */
    public ContactExhibitionDto save(ContactRegisterDto contactRegisterDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRegisterDto, contact);
        return new ContactExhibitionDto(contactRepository.save(contact));
    }

    /**
     * Returns a contact by its ID.
     *
     * @param id the contact ID
     * @return the contact if found
     * @throws RuntimeException if the contact is not found
     */
    public ContactExhibitionDto searchById(Long id) {

        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (contactOptional.isPresent()) {
            return new ContactExhibitionDto(contactOptional.get());
        } else {
            throw new UserNotFindException("Contact not found");
        }
    }

    /**
     * Returns a list of all contacts.
     *
     * @return list of contacts
     */
    public List<Contact> listAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Deletes a contact by its ID.
     *
     * @param id the contact ID
     * @throws RuntimeException if the contact is not found
     */
    public void delete(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (contactOptional.isPresent()) {
            contactRepository.delete(contactOptional.get());
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    /**
     * Returns a list of contacts whose birthdays fall between the given dates.
     *
     * @param from the start date of the range
     * @param to   the end date of the range
     * @return list of contacts with birthdays in the given range
     */
    public List<Contact> getBirthdayCelebrants(LocalDate from, LocalDate to) {

        return contactRepository.findByBirthDateBetween(from, to);

    }


    /**
     * Updates an existing contact in the database.
     *
     * @param contact the contact with updated data
     * @return the updated contact
     * @throws RuntimeException if the contact is not found
     */
    public Contact update(Contact contact) {
        Optional<Contact> contactOptional = contactRepository.findById(contact.getId());

        if (contactOptional.isPresent()) {
            return contactRepository.save(contact);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    /**
     * Finds a contact by their name.
     *
     * @param name the name of the contact
     * @return the contact with the given name
     * @throws RuntimeException if the contact is not found
     */
    public Contact findByName(String name) {
        Optional<Contact> contactOptional = contactRepository.findByName(name);

        if (contactOptional.isPresent()) {
            return contactOptional.get();
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

}
