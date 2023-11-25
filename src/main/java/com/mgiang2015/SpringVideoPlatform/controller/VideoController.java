package com.mgiang2015.SpringVideoPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgiang2015.SpringVideoPlatform.repository.VideoRepository;
import com.mgiang2015.SpringVideoPlatform.exception.VideoNotFoundException;
import com.mgiang2015.SpringVideoPlatform.model.Video;

@RestController
public class VideoController {
    @Autowired
    private VideoRepository repository;

    @GetMapping("/videos")
    public List<Video> all() {
        return repository.findAll();
    }

    @PostMapping("/videos")
    public Video newVideo(@RequestBody Video newVideo) {
        return repository.save(newVideo);
    }

    @GetMapping("/videos/{id}")
    public Video one(@PathVariable Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new VideoNotFoundException(id));
    }

    @PutMapping("/videos/{id}")
    public Video replaceVideo(@RequestBody Video newVideo, @PathVariable Long id) {
        return repository.findById(id)
        .map(video -> {
            video.setName(newVideo.getName());
            video.setUrl(newVideo.getUrl());
            
            return repository.save(video);
        }).orElseGet(() -> {
            newVideo.setId(id);
            return repository.save(newVideo);
        });
    }

    @DeleteMapping("/videos/{id}")
    public void deleteVideo(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
