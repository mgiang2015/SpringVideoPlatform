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

import com.mgiang2015.SpringVideoPlatform.exception.CourseNotFoundException;
import com.mgiang2015.SpringVideoPlatform.model.Course;
import com.mgiang2015.SpringVideoPlatform.repository.CourseRepository;



@RestController
public class CourseController {
    @Autowired
    private CourseRepository repository;

    @GetMapping("/courses")
    public List<Course> all() {
        return repository.findAll();
    }
    
    @PostMapping("/courses")
    public Course newCourse(@RequestParam("title") String title) {
        // We should only need title to produce a new course
        Course course = new Course();
        course.setTitle(title);

        return repository.save(course);
    }
    
    @GetMapping("/courses/{id}")
    public Course one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PutMapping("courses/{id}")
    public Course replaceCourse(@PathVariable Long id, @RequestBody Course newCourse) {
        return repository.findById(id)
        .map(course -> {
            course.setTitle(newCourse.getTitle());
            course.setDescription(newCourse.getDescription());
            course.setImgUrl(newCourse.getImgUrl());
            course.setPrice(newCourse.getPrice());
            course.setPublished(newCourse.getPublished());;
            return repository.save(course);
        }).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @DeleteMapping("courses/{id}")
    public void deleteCourse(@PathVariable Long id) throws IOException {
        Course course = repository.findById(id)
            .orElseThrow(() -> new CourseNotFoundException(id));
        
        // Delete
        repository.deleteById(id);
    }
}
