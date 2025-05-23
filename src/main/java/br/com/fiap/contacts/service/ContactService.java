package br.com.fiap.contacts.service;

import br.com.fiap.contacts.dto.ContactExhibitionDto;
import br.com.fiap.contacts.dto.ContactRegisterDto;
import br.com.fiap.contacts.exception.UserNotFoundException;
import br.com.fiap.contacts.model.Contact;
import br.com.fiap.contacts.repository.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            throw new UserNotFoundException("Contact not found");
        }
    }

    /**
     * Returns a paginated list of all contacts.
     *
     * @param pageable pagination information
     * @return a page of contacts converted to ContactExhibitionDto
     */
    public Page<ContactExhibitionDto> listAllContacts(Pageable pageable) {
        return contactRepository
                .findAll(pageable)
                .map(ContactExhibitionDto::new);
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
            throw new UserNotFoundException("Contact not found");
        }
    }

    /**
     * Returns a list of contacts whose birthdays fall between the given dates.
     *
     * @param initialDate the start date of the range
     * @param finalDate   the end date of the range
     * @return list of contacts with birthdays in the given range
     */
    public List<ContactExhibitionDto> getBirthdayCelebrants(LocalDate initialDate, LocalDate finalDate) {

        return contactRepository
                .findByBirthDateBetween(initialDate, finalDate)
                .stream()
                .map(ContactExhibitionDto::new)
                .toList();


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
            throw new UserNotFoundException("Contact not found");
        }
    }

    /**
     * Finds a contact by their name.
     *
     * @param name the name of the contact
     * @return the contact with the given name
     * @throws RuntimeException if the contact is not found
     */
    public ContactExhibitionDto findByName(String name) {
        Optional<Contact> contactOptional = contactRepository.findByName(name);

        if (contactOptional.isPresent()) {
            return new ContactExhibitionDto(contactOptional.get());
        } else {
            throw new UserNotFoundException("Contact not found");
        }
    }

    /**
     * Searches for a contact by email.
     *
     * @param email the email address of the contact to search for
     * @return the contact with the specified email wrapped in ContactExhibitionDto
     * @throws UserNotFoundException if no contact with the given email is found
     */
    public ContactExhibitionDto findByEmail(String email) {
        Optional<Contact> contactOptional = contactRepository.findByEmail(email);

        if (contactOptional.isPresent()) {
            return new ContactExhibitionDto(contactOptional.get());
        } else {
            throw new UserNotFoundException("Contact not found");
        }

    }

}
