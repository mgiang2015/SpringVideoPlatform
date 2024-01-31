package com.mgiang2015.SpringVideoPlatform.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgiang2015.SpringVideoPlatform.exception.UserNotFoundException;
import com.mgiang2015.SpringVideoPlatform.model.User;
import com.mgiang2015.SpringVideoPlatform.repository.UserRepository;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    public User newUser(@RequestParam(name = "email") String email, 
                        @RequestParam(name = "password") String password, 
                        @RequestParam(name = "role", defaultValue = "USER") String role) throws NoSuchAlgorithmException {
        User newUser = createNewUser(email, password, role);

        // return new user
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public User one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@PathVariable Long id, @RequestParam(name = "email") String email,
                        @RequestParam(name = "role", defaultValue = "") String role) {
        return repository.findById(id)
        .map(user -> {
            user.setEmail(email);
            if (role != "") {
                user.setRole(role);
            }
            return repository.save(user);
        })
        .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // helper function
    private User createNewUser(String email, String password, String role) throws NoSuchAlgorithmException {
        // Create new user
        User newUser = new User();

        // set email and role
        newUser.setEmail(email);
        newUser.setRole(role);

        // generate salt and password hashing
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // save salt and hashed password
        newUser.setSalt(salt);
        newUser.setHashedPassword(hashedPassword);

        return newUser;
    }
}
