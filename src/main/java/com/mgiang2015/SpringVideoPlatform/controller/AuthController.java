package com.mgiang2015.SpringVideoPlatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mgiang2015.SpringVideoPlatform.model.User;
import com.mgiang2015.SpringVideoPlatform.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam(name = "email") String email, 
                        @RequestParam(name = "password") String password) throws NoSuchAlgorithmException {
        
        List<User> userByEmail = userRepository.findAll().stream()
                                .filter(user -> user.getEmail().equals(email))
                                .toList();

        if (userByEmail.isEmpty()) {
            return "Unsuccessful. No user with provided email is found";
        }

        // Match hashed password
        User user = userByEmail.get(0);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(user.getSalt());
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        
        if (Arrays.equals(hashedPassword, user.getHashedPassword())) {
            return "Login successful!"; // return a token or something
        } else {
            return "Unsuccesssful, wrong password provided";
        }
    }
    
}