package com.mgiang2015.SpringVideoPlatform.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mgiang2015.SpringVideoPlatform.exception.ChapterNotFoundException;
import com.mgiang2015.SpringVideoPlatform.exception.CourseNotFoundException;
import com.mgiang2015.SpringVideoPlatform.model.Chapter;
import com.mgiang2015.SpringVideoPlatform.model.Course;
import com.mgiang2015.SpringVideoPlatform.model.VideoData;
import com.mgiang2015.SpringVideoPlatform.repository.ChapterRepository;
import com.mgiang2015.SpringVideoPlatform.repository.CourseRepository;
import com.mgiang2015.SpringVideoPlatform.services.VideoMongodbService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ChapterController {
    @Autowired
    private ChapterRepository repository;
    @Autowired
    private VideoMongodbService service;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/chapters")
    public List<Chapter> all() {
        return repository.findAll();
    }

    @PostMapping("/chapters")
    public Chapter newChapter(@RequestParam("courseId") Long courseId, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {  
        Chapter newChapter = new Chapter();
        newChapter.setTitle(title);
        newChapter.setDescription(description);

        if (file != null) {
            String id = service.addVideo(file);
            newChapter.setVideoId(id); // video id generated by mongodb
        }

        Course result = courseRepository.findById(courseId)
        .map(course -> {
            course.addChapter(newChapter);
            return courseRepository.save(course);
        }).orElseThrow(() -> new CourseNotFoundException(courseId));

        return result.getChapters().get(result.getChapters().size() - 1); // get the last entry
    }

    @GetMapping("/chapters/{id}")
    public Chapter one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new ChapterNotFoundException(id));
    }
    
    @GetMapping("/chapters/{id}/stream")
    public void streamVideo(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Chapter c = repository.findById(id)
            .orElseThrow(() -> new ChapterNotFoundException(id));
        VideoData data = service.getVideo(c.getVideoId());
        response.addHeader("Content-Type", "video/mp4");
        FileCopyUtils.copy(data.getStream(), response.getOutputStream());
    }

    @PutMapping("/chapters/{id}")
    public Chapter replaceChapter(@PathVariable Long id, @RequestParam("courseId") Long courseId, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        return repository.findById(id)
        .map(chapter -> {
            chapter.setTitle(title); // update in SQL
            chapter.setDescription(description);
            if (file != null) {
                service.deleteVideo(chapter.getVideoId());
                try {
                    String newVideoId = service.addVideo(file);
                    chapter.setVideoId(newVideoId); // video id generated by mongodb
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            return repository.save(chapter);
        }).orElseThrow(() -> new ChapterNotFoundException(id));
    }

    @DeleteMapping("/chapters/{id}")
    public void deleteChapter(@RequestParam("courseId") Long courseId, @PathVariable Long id) throws IOException {
        // Delete from mongodb first
        Chapter c = repository.findById(id)
            .orElseThrow(() -> new ChapterNotFoundException(id));
        service.deleteVideo(c.getVideoId());

        repository.delete(c);

        // unlink from course
        courseRepository.findById(courseId)
        .map(course -> {
            course.removeChapterById(id);
            return courseRepository.save(course);
        }).orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    @DeleteMapping("/chapters/all")
    public void deleteAllChapters(@RequestParam("courseId") Long courseId) {
        // not sure if this works. Check
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        List<Chapter> allChapters = course.getChapters();
        
        // Delete chapters
        for (Chapter chapter : allChapters) {
            service.deleteVideo(chapter.getVideoId());
            repository.deleteById(chapter.getId());
        }

        // unlink chapters
        course.setChapters(new ArrayList<>());
        courseRepository.save(course);
    }
}
