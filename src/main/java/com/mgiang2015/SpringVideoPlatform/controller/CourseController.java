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
import org.springframework.web.multipart.MultipartFile;

import com.mgiang2015.SpringVideoPlatform.exception.CourseNotFoundException;
import com.mgiang2015.SpringVideoPlatform.model.Chapter;
import com.mgiang2015.SpringVideoPlatform.model.Course;
import com.mgiang2015.SpringVideoPlatform.repository.ChapterRepository;
import com.mgiang2015.SpringVideoPlatform.repository.CourseRepository;
import com.mgiang2015.SpringVideoPlatform.services.VideoMongodbService;



@RestController
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VideoMongodbService service;

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

    // @PostMapping("courses/{id}/chapters")
    // Commenting this out, POST related to chapter should be in chapter
    // public Course uploadChapter(@PathVariable Long id,
    //             @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("file") MultipartFile file) throws IOException {
        
    //     String videoId = service.addVideo(file);

    //     Chapter newChapter = new Chapter();
    //     newChapter.setTitle(title);
    //     newChapter.setDescription(description);
    //     newChapter.setVideoId(videoId); // video id generated by mongodb

    //     return courseRepository.findById(id)
    //     .map(course -> {
    //         course.addChapter(newChapter);
    //         return courseRepository.save(course);
    //     }).orElseThrow(() -> new CourseNotFoundException(id));
    // }

    // @DeleteMapping("courses/{courseId}/chapters/{chapterId}")
    // public Course deleteChapter(@PathVariable Long courseId, @PathVariable Long chapterId) {
        
    //     // Assume that chapter is already gone in "chapters" table
        
    //     return courseRepository.findById(courseId)
    //     .map(course -> {
    //         course.removeChapterById(chapterId);
    //         return courseRepository.save(course);
    //     }).orElseThrow(() -> new CourseNotFoundException(courseId));
    // }

    @DeleteMapping("courses/{id}")
    public void deleteCourse(@PathVariable Long id) throws IOException {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CourseNotFoundException(id));
        
        // Delete
        courseRepository.deleteById(course.getId());
    }
}
