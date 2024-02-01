package com.mgiang2015.SpringVideoPlatform;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mgiang2015.SpringVideoPlatform.model.Chapter;
import com.mgiang2015.SpringVideoPlatform.model.Course;
import com.mgiang2015.SpringVideoPlatform.repository.ChapterRepository;
import com.mgiang2015.SpringVideoPlatform.repository.CourseRepository;

@Configuration
public class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean // We don't need this with persisting mysql database
    CommandLineRunner initCourseDb(CourseRepository courseRepo, ChapterRepository chapterRepo) {
        return args -> {

            // check if courseRepo is empty. Only initialize if empty
            if (courseRepo.findAll().isEmpty()) {

                // Create chapters
                List<Chapter> chapters = new ArrayList<>();

                // Create 3 new chapters
                Chapter chapter1 = new Chapter();
                chapter1.setTitle("Chapter 1: What is Computer Science?");
                chapter1.setDescription("This chapter describes the overall concept of Computer Science field, and why we learn it.");
                chapters.add(chapter1);

                Chapter chapter2 = new Chapter();
                chapter2.setTitle("Chapter 2: Environment Setup");
                chapter2.setDescription("This chapter walks you through everything you need to set up on your computer to complete the hands-on exercises later on.");
                chapters.add(chapter2);

                Chapter chapter3 = new Chapter();
                chapter3.setTitle("Chapter 3: hello-world.py");
                chapter3.setDescription("This chapter walks you through writing your first-ever python program, and run it successfully.");
                chapters.add(chapter3);

                // Update course
                Course course = new Course();
                course.setTitle("Computer Science for Beginners in Python");
                course.setDescription("Your first steps towards Computer Science");
                course.setPrice(Float.valueOf("50.00"));
                course.setChapters(chapters);
                courseRepo.save(course);
                log.info("Created demo courses and chapters");
            }
        };
    }
}
