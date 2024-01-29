package com.mgiang2015.SpringVideoPlatform;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mgiang2015.SpringVideoPlatform.model.Chapter;
import com.mgiang2015.SpringVideoPlatform.model.Course;
import com.mgiang2015.SpringVideoPlatform.model.User;
import com.mgiang2015.SpringVideoPlatform.repository.ChapterRepository;
import com.mgiang2015.SpringVideoPlatform.repository.CourseRepository;
import com.mgiang2015.SpringVideoPlatform.repository.UserRepository;

@Configuration
public class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean // We don't need this with persisting mysql database
    CommandLineRunner initCourseDb(CourseRepository courseRepo, ChapterRepository chapterRepo) {
        return args -> {
            // Clear database first
            chapterRepo.deleteAll();
            courseRepo.deleteAll();

            // Create chapters
            List<Chapter> chapters = new ArrayList<>();

            // Create 3 new chapters
            Chapter chapter1 = new Chapter();
            chapter1.setTitle("The beninging");
            chapter1.setDescription("First chapter");
            // chapters.add(chapterRepo.save(chapter1));
            chapters.add(chapter1);

            Chapter chapter2 = new Chapter();
            chapter2.setTitle("Exodus");
            chapter2.setDescription("Yes yes desc desc");
            // chapters.add(chapterRepo.save(chapter2));
            chapters.add(chapter2);

            Chapter chapter3 = new Chapter();
            chapter3.setTitle("Once upon a Bean");
            chapter3.setDescription("Jack the beanstalk enjoyer");
            // chapters.add(chapterRepo.save(chapter3));
            chapters.add(chapter3);

            // Update course
            Course course = new Course();
            course.setTitle("Komputer 4 Kids");
            course.setDescription("Hello world and dynamic programming");
            course.setPrice(Float.valueOf("45.55"));
            course.setChapters(chapters);
            courseRepo.save(course);
        };
    }
}
