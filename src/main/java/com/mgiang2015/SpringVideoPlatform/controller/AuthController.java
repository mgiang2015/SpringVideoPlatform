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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;

    @Value("${okta.oauth2.issuer}")
    private String successIssuer;

    @Value("${okta.oauth2.audience}")
    private String successAudience;

    @PostMapping("/login")
    public ResponseEntity<LoginReturnValue> login(@RequestParam(name = "email") String email, 
                        @RequestParam(name = "password") String password) throws NoSuchAlgorithmException {
        
        List<User> userByEmail = userRepository.findAll().stream()
                                .filter(user -> user.getEmail().equals(email))
                                .toList();

        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().body(new LoginReturnValue());
        }

        // Match hashed password
        User user = userByEmail.get(0);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(user.getSalt());
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        
        if (Arrays.equals(hashedPassword, user.getHashedPassword())) {
            return ResponseEntity.ok().body(new LoginReturnValue(successIssuer, successAudience));
        } else {
            return ResponseEntity.badRequest().body(new LoginReturnValue());
        }
    }
}

class LoginReturnValue {
    private String issuer;
    private String audience;

    public LoginReturnValue() {
        this.issuer = "";
        this.audience = "";
    }

    public LoginReturnValue(String issuer, String audience) {
        this.issuer = issuer;
        this.audience = audience;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getAudience() {
        return audience;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
