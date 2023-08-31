package com.shopme.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopme.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	
}
