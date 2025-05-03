package br.com.fiap.contacts.service;

import br.com.fiap.contacts.dto.UserExhibitionDto;
import br.com.fiap.contacts.dto.UserRegisterDto;
import br.com.fiap.contacts.exception.UserNotFoundException;
import br.com.fiap.contacts.model.User;
import br.com.fiap.contacts.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for business logic related to User entities.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a user to the database.
     *
     * @param userDto the user to save
     * @return the saved user as UserExhibitionDto
     */
    public UserExhibitionDto save(UserRegisterDto userDto) {

        String passwordCrypto = new BCryptPasswordEncoder().encode(userDto.password());

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordCrypto);

        User userRegistered = userRepository.save(user);
        return new UserExhibitionDto(userRepository.save(user));
    }

    /**
     * Returns all users.
     *
     * @return list of users as UserExhibitionDto
     */
    public List<UserExhibitionDto> listAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserExhibitionDto::new)
                .toList();
    }

    /**
     * Returns a user by their ID.
     *
     * @param id the user ID
     * @return the user as UserExhibitionDto
     * @throws UserNotFoundException if user is not found
     */
    public UserExhibitionDto findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return new UserExhibitionDto(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the user ID
     * @throws UserNotFoundException if user is not found
     */
    public void delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Updates an existing user.
     *
     * @param user the user with updated data
     * @return the updated user
     * @throws UserNotFoundException if user is not found
     */
    public User update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if (userOptional.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
