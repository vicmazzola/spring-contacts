package br.com.fiap.contacts.controller;

import br.com.fiap.contacts.dto.UserExhibitionDto;
import br.com.fiap.contacts.dto.UserRegisterDto;
import br.com.fiap.contacts.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        userRegisterDto.email(),
                        userRegisterDto.password()
                );

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserExhibitionDto register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        UserExhibitionDto userRegistered = null;
        userRegistered = service.save(userRegisterDto);
        return userRegistered;
    }

}
