package com.mgiang2015.SpringVideoPlatform.model;

import java.util.Collection;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date lastModifiedAt;

    private String title;
        
    // Bidirectional OneToMany relationship
    @OneToMany(mappedBy = "category")
    private Collection<Course> courses;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
