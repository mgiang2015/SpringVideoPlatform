package com.mgiang2015.SpringVideoPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgiang2015.SpringVideoPlatform.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
