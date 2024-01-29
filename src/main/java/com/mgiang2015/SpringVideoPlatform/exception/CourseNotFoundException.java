package com.mgiang2015.SpringVideoPlatform.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Could not find course " + id);
    }
}
