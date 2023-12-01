package com.mgiang2015.SpringVideoPlatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mgiang2015.SpringVideoPlatform.model.User;
import com.mgiang2015.SpringVideoPlatform.model.Video;
import com.mgiang2015.SpringVideoPlatform.repository.UserRepository;
import com.mgiang2015.SpringVideoPlatform.repository.VideoRepository;

@Configuration
public class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initUserDb(UserRepository userRepo) {
        return args -> {
            User giang = new User();
            giang.setEmail("giang@gmail.com");
            giang.setPassword("giang");

            User phuc = new User();
            phuc.setEmail("phuc@gmail.com");
            phuc.setPassword("phuc");

            log.info("Preloading " + userRepo.save(giang));
            log.info("Preloading " + userRepo.save(phuc));

        };
    }

    @Bean
    CommandLineRunner initVideoDb(VideoRepository videoRepo) {
        return args -> {
            Video v1 = new Video();
            v1.setName("MY FAVORITE SONG RIGHT NOW");
            v1.setUrl("https://www.youtube.com/watch?v=m-M1AtrxztU");

            Video v2 = new Video();
            v2.setName("MY 2ND FAVORITE SONG AS OF 1 DEC 2023");
            v2.setUrl("https://www.youtube.com/watch?v=HmAsUQEFYGI");

            log.info("Preloading " + videoRepo.save(v1));
            log.info("Preloading " + videoRepo.save(v2));
        };
    }
}