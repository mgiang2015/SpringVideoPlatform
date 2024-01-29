package com.mgiang2015.SpringVideoPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgiang2015.SpringVideoPlatform.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
