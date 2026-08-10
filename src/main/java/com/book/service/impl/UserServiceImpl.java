package com.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.book.model.User;
import com.book.repository.UserRepository;
import com.book.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean saveUser(User user) {
	    user.setRole("USER");
	    user.setEmail(user.getEmail().toLowerCase().trim());

	    User existingUser = userRepo.findByEmail(user.getEmail());
	    if (existingUser != null) {
	        return false; // email already exists
	    }

	    try {
	        userRepo.save(user);
	        return true;
	    } catch (DataIntegrityViolationException e) {
	        // In case two users submit at the same time
	        return false;
	    }
	}


	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> getUsers(String role) {
		return userRepo.findByRole(role);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

	@Override
	public User saveAdmin(User user) {
		user.setRole("ADMIN");
		return userRepo.save(user)  ;
	}

	@Override
	public boolean existsEmail(String email) {
		return userRepo.existsByEmail(email) ;
	}

	@Override
	public User loginUser(String email, String password) {
		User validUser  = userRepo.findByEmail(email);
		
		if(validUser != null && validUser.getPassword().equals(password))
		{
			return validUser;
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
