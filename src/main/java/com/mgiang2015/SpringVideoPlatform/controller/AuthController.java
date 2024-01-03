package com.mgiang2015.SpringVideoPlatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mgiang2015.SpringVideoPlatform.model.User;
import com.mgiang2015.SpringVideoPlatform.repository.UserRepository;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    public ResponseEntity<String> login(@RequestParam(name = "email") String email, 
                        @RequestParam(name = "password") String password) throws NoSuchAlgorithmException, IOException, InterruptedException {
        
        List<User> userByEmail = userRepository.findAll().stream()
                                .filter(user -> user.getEmail().equals(email))
                                .toList();

        if (userByEmail.isEmpty()) {
            return ResponseEntity.badRequest().body("Fail");
        }

        // Match hashed password
        User user = userByEmail.get(0);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(user.getSalt());
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        
        if (Arrays.equals(hashedPassword, user.getHashedPassword())) {
            // call auth0 for token
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(successIssuer + "oauth/token"))
                .header("content-type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s&audience=%s", DotEnvReader.getClientId(), DotEnvReader.getClientSecret(), successAudience)))
                .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.ok().body(response.body());
        } else {
            return ResponseEntity.badRequest().body("Fail");
        }
    }
}

class DotEnvReader {

    private static final Dotenv READER = Dotenv.load();

    public static String getClientId() {
        return READER.get("SPRINGBOOT_AUTH0_CLIENT_ID");
    }

    public static String getClientSecret() {
        return READER.get("SPRINGBOOT_AUTH0_CLIENT_SECRET");
    }
}
