package com.mgiang2015.SpringVideoPlatform.exception;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
