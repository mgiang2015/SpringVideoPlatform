package com.mgiang2015.SpringVideoPlatform.exception;

public class ChapterNotFoundException extends RuntimeException {
        
    public ChapterNotFoundException(Long id) {
        super("Could not find chapter " + Long.toString(id));
    }
}
