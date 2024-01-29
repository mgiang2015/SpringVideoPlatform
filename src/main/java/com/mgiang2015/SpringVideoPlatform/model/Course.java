package com.mgiang2015.SpringVideoPlatform.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private String description = "";
    private String imgUrl = ""; // course thumbnail
    private Float price = Float.valueOf(0);
    private boolean isPublished = false;

    // Unidirectional OneToMany relationship
    @OneToMany(cascade = CascadeType.ALL) // cascade delete
    @JoinColumn(name = "course_id") // join column will be in chapters table
    private List<Chapter> chapters = new ArrayList<>();
    
    // Bidirectional ManyToOne relationship
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    
    // // Many to One - One creator can create many courses
    // User creator

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
    public boolean getPublished() {
        return isPublished;
    }
    public List<Chapter> getChapters() {
        return chapters;
    }
    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }
    public void removeChapter(Chapter chapter) {
        this.chapters.remove(chapter);
    }

    public void removeChapterById(Long chapterId) {
        this.chapters.removeIf(chapter -> chapter.getId().equals(chapterId));
    }
}
