package com.mgiang2015.SpringVideoPlatform.exception;

public class ChapterNotFoundException extends RuntimeException {
        
    public ChapterNotFoundException(String id) {
        super("Could not find chapter " + id);
    }
}
