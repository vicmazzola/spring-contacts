package br.com.fiap.contacts.controller;

import br.com.fiap.contacts.dto.UserExhibitionDto;
import br.com.fiap.contacts.dto.UserRegisterDto;
import br.com.fiap.contacts.model.User;
import br.com.fiap.contacts.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling User-related HTTP requests.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Creates a new user.
     *
     * @param userRegisterDto the user data to register
     * @return the created user as UserExhibitionDto
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserExhibitionDto save(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return service.save(userRegisterDto);
    }

    /**
     * Lists all users.
     *
     * @return list of users
     */
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserExhibitionDto> listAll() {
        return service.listAll();
    }

    /**
     * Finds a user by ID.
     *
     * @param id the user ID
     * @return the user as UserExhibitionDto
     */
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserExhibitionDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the user ID
     */
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    /**
     * Updates an existing user.
     *
     * @param user the user with updated data
     * @return the updated user
     */
    @PutMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user) {
        return service.update(user);
    }
}
