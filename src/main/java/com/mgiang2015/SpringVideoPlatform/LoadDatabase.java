package com.mgiang2015.SpringVideoPlatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mgiang2015.SpringVideoPlatform.model.User;
import com.mgiang2015.SpringVideoPlatform.repository.UserRepository;

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
}
