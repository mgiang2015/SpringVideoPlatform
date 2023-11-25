package com.mgiang2015.SpringVideoPlatform.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue
    private Long id;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;

    private String url;
    private String name;
    
    public Long getId() {
        return id;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    // public User getCreator() {
    //     return creator;
    // }
    // public void setCreator(User creator) {
    //     this.creator = creator;
    // }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}
