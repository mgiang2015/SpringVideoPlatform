package com.mgiang2015.SpringVideoPlatform.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date lastModifiedAt;

    private String title;
    private String description;
    private String imgUrl; // course thumbnail
    private Float price;
    private boolean isPublished;
    
    // // Many to One - One Category can have many courses
    // Category
    
    // // Many to One - One creator can create many courses
    // User creator

    // One to Many - One Course can have many videos / chapters
    // Video[]

    public Long getId() {
        return id;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public Float getPrice() {
        return price;
    }
    public boolean isPublished() {
        return isPublished;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

}
