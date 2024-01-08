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
}
