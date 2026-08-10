package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
	public List<User> findByRole(String role);
	
	public Boolean existsByEmail(String email);
	
	public List<User> findAll();
}
