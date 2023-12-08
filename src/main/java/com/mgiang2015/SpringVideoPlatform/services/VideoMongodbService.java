package com.mgiang2015.SpringVideoPlatform.services;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mgiang2015.SpringVideoPlatform.model.VideoData;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class VideoMongodbService {
    
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;
    
    public String addVideo(String title, MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "video");
        metaData.put("title", title);
        metaData.put("fileSize", file.getSize());
        ObjectId id = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType(), metaData);
        return id.toString();
    }

    public VideoData getVideo(String id) throws IllegalStateException, IOException {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        VideoData videoData = new VideoData();
        videoData.setTitle(file.getMetadata().get("title").toString());
        videoData.setStream(operations.getResource(file).getInputStream());
        return videoData;
    }

    public void deleteVideo(String id) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }
}
