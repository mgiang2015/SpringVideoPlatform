package com.mgiang2015.SpringVideoPlatform.model;

import java.io.InputStream;

/** 
 * Actual video data that will be stored in MongoDB
*/
public class VideoData {
    private InputStream stream;

    public InputStream getStream() {
        return stream;
    }
    public void setStream(InputStream stream) {
        this.stream = stream;
    }
    
}
