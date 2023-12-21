package com.mgiang2015.SpringVideoPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgiang2015.SpringVideoPlatform.model.User;
import com.mgiang2015.SpringVideoPlatform.repository.UserRepository;
import com.mgiang2015.SpringVideoPlatform.exception.UserNotFoundException;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public User one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id)
        .map(user -> {
            user.setEmail(newUser.getEmail());
            return repository.save(user);
        })
        .orElseGet(() -> {
            newUser.setId(id);
            return repository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
