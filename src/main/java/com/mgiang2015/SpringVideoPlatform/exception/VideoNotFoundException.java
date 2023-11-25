package com.mgiang2015.SpringVideoPlatform.exception;

public class VideoNotFoundException extends RuntimeException {
        
    public VideoNotFoundException(Long id) {
        super("Could not find video " + id);
    }
}
