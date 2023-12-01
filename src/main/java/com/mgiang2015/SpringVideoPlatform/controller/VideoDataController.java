package com.mgiang2015.SpringVideoPlatform.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mgiang2015.SpringVideoPlatform.services.VideoDataService;
import com.mgiang2015.SpringVideoPlatform.model.VideoData;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class VideoDataController {
    
    @Autowired
    private VideoDataService service;

    @PostMapping("/videos/add")
    public String addVideo(@RequestParam("title") String title, @RequestParam("file") MultipartFile file) throws IOException {
        String id = service.addVideo(title, file);
        return id.toString();
    }

    @GetMapping("/videos/details/{id}")
    public VideoData getVideo(@PathVariable String id) throws IllegalStateException, IOException {
        VideoData data = service.getVideo(id);
        return data;
    }
    
    @GetMapping("/videos/stream/{id}")
    public void streamVideo(@PathVariable String id, HttpServletResponse response) throws IOException {
        VideoData data = service.getVideo(id);
        FileCopyUtils.copy(data.getStream(), response.getOutputStream());
    }
}