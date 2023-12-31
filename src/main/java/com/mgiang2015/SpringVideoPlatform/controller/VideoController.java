package com.mgiang2015.SpringVideoPlatform.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mgiang2015.SpringVideoPlatform.repository.VideoRepository;
import com.mgiang2015.SpringVideoPlatform.services.VideoMongodbService;

import jakarta.servlet.http.HttpServletResponse;

import com.mgiang2015.SpringVideoPlatform.exception.VideoNotFoundException;
import com.mgiang2015.SpringVideoPlatform.model.Video;
import com.mgiang2015.SpringVideoPlatform.model.VideoData;

@RestController
public class VideoController {
    @Autowired
    private VideoRepository repository;
    @Autowired
    private VideoMongodbService service;

    @GetMapping("/videos")
    public List<Video> all() {
        return repository.findAll();
    }

    @PostMapping("/videos")
    public Video newVideo(@RequestParam("title") String title, @RequestParam("file") MultipartFile file) throws IOException {
        String id = service.addVideo(file);
        Video newVideo = new Video();
        newVideo.setTitle(title);
        newVideo.setId(id); // video id generated by mongodb

        return repository.save(newVideo);
    }

    @GetMapping("/videos/{id}")
    public Video one(@PathVariable String id) {
        return repository.findById(id)
        .orElseThrow(() -> new VideoNotFoundException(id));
    }
    
    @GetMapping("/videos/stream/{id}")
    public void streamVideo(@PathVariable String id, HttpServletResponse response) throws IOException {
        VideoData data = service.getVideo(id);
        response.addHeader("Content-Type", "video/mp4");
        FileCopyUtils.copy(data.getStream(), response.getOutputStream());
    }

    @PutMapping("/videos/{id}")
    public Video replaceVideo(@RequestBody Video newVideo, @PathVariable String id) {
        return repository.findById(id)
        .map(video -> {
            video.setTitle(newVideo.getTitle()); // update in SQL
            return repository.save(video);
        }).orElseGet(() -> {
            newVideo.setId(id);
            return repository.save(newVideo);
        });
    }

    @DeleteMapping("/videos/{id}")
    public void deleteVideo(@PathVariable String id) {
        // Delete from mongodb first
        service.deleteVideo(id);
        repository.deleteById(id);
    }

    @DeleteMapping("/videos/all")
    public void deleteAllVideos() {
        List<Video> allVideos = repository.findAll();
        for (Video video : allVideos) {
            service.deleteVideo(video.getId());
            repository.deleteById(video.getId());
        }
    }
}
