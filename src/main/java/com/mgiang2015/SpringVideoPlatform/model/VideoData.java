package com.mgiang2015.SpringVideoPlatform.model;

import java.io.InputStream;

/** 
 * Actual video data that will be stored in MongoDB
*/
public class VideoData {
    private String title;
    private InputStream stream;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public InputStream getStream() {
        return stream;
    }
    public void setStream(InputStream stream) {
        this.stream = stream;
    }
    @Override
    public String toString() {
        return "VideoData [title=" + title + ", stream=" + stream + "]";
    }

    
}
