package com.mgiang2015.SpringVideoPlatform.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgiang2015.SpringVideoPlatform.exception.ChapterNotFoundException;
import com.mgiang2015.SpringVideoPlatform.exception.CourseNotFoundException;
import com.mgiang2015.SpringVideoPlatform.model.Chapter;
import com.mgiang2015.SpringVideoPlatform.model.Course;
import com.mgiang2015.SpringVideoPlatform.repository.ChapterRepository;
import com.mgiang2015.SpringVideoPlatform.repository.CourseRepository;



@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @GetMapping("/courses")
    public List<Course> all() {
        return courseRepository.findAll();
    }
    
    @PostMapping("/courses")
    public Course newCourse(@RequestParam("title") String title) {
        // We should only need title to produce a new course
        Course course = new Course();
        course.setTitle(title);

        return courseRepository.save(course);
    }
    
    @GetMapping("/courses/{id}")
    public Course one(@PathVariable Long id) {
        return courseRepository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PutMapping("courses/{id}")
    public Course replaceCourse(@PathVariable Long id, @RequestBody Course newCourse) {
        return courseRepository.findById(id)
        .map(course -> {
            course.setTitle(newCourse.getTitle());
            course.setDescription(newCourse.getDescription());
            course.setImgUrl(newCourse.getImgUrl());
            course.setPrice(newCourse.getPrice());
            course.setPublished(newCourse.getPublished());;
            return courseRepository.save(course);
        }).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PutMapping("courses/{id}/chapters")
    public Course uploadChapter(@PathVariable Long id,
                @RequestParam("chapterId") Long chapterId) {
        
        Chapter chapter = chapterRepository.findById(chapterId)
        .orElseThrow(() -> new ChapterNotFoundException(chapterId));

        return courseRepository.findById(id)
        .map(course -> {
            course.addChapter(chapter);
            return courseRepository.save(course);
        }).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @DeleteMapping("courses/{id}/chapters")
    public Course deleteChapter(@PathVariable Long id,
                @RequestParam("chapterId") Long chapterId) {
        
        // Assume that chapter is already gone in "chapters" table
        
        return courseRepository.findById(id)
        .map(course -> {
            course.removeChapterById(chapterId);
            return courseRepository.save(course);
        }).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @DeleteMapping("courses/{id}")
    public void deleteCourse(@PathVariable Long id) throws IOException {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CourseNotFoundException(id));
        
        // Delete
        courseRepository.deleteById(course.getId());
    }
}