package com.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.book.model.User;


public interface UserService {

	public boolean saveUser(User user);
	
	public User loginUser(String email, String password);
	
	public User getUserByEmail(String email);
	
	public List<User> getUsers(String role);
	
	public List<User> getAllUsers();
	
	public User updateUser(User user);
	
	public void deleteUser(int id);
	
	public User saveAdmin(User user);
	
	public boolean existsEmail(String email);
	
}
