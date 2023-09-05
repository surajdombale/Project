package com.shopme.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopme.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
