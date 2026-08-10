package com.book.controller;

import org.springframework.web.servlet.mvc.support.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.model.Book;
import com.book.model.User;
import com.book.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/about")
	public String openAboutPage() {
		return "about";
	}
	
	@GetMapping("/regForm")								//create blank user object
	public String openRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitRegisterForm(@Valid @ModelAttribute("user") User user,
	                                 BindingResult result,
	                                 Model model) {

	    // 1️⃣ Validation errors (email format, blank, etc.)
	    if (result.hasErrors()) {
	        return "register"; // Shows errors in the form
	    }

	    // 2️⃣ Check for duplicate email
	    boolean status = userService.saveUser(user);
	    if(!status) {
	        model.addAttribute("errorMsg", "Email already registered");
	        return "register";
	    }

	    // 3️⃣ Success
	    model.addAttribute("successMsg", "Registered Successfully");
	    model.addAttribute("user", new User()); // reset form
	    return "register";
	}

	
	@GetMapping("/loginForm")
	public String openLoginForm(Model model)
	{
	model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("user") User user, Model model, HttpSession session) {
		User validUser = userService.loginUser(user.getEmail(),user.getPassword());
		if(validUser != null)
		{
			session.setAttribute("loggedInUser", validUser);
			return "redirect:/";
		}
		else
		{
			model.addAttribute("errorMsg", "Email id and password didn't matched");
			return "login";
		}
		
	}
	
	
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // removes user session
        return "redirect:/"; // back to homepage
    }
    
    @GetMapping("/available_users")
    public String getAllUsers(Model model) {
    	List<User> list = userService.getAllUsers();
    	model.addAttribute("user", list);
    	return "userList";
    }
    
	/*
	 * @GetMapping("/available_books") 
	 * public String getAllBooks(Model model) {
	 * List<Book> list = bookService.getAllBooks(); model.addAttribute("book",
	 * list); return "bookList"; }
	 */
    
	/*
	 * @GetMapping("/deleteBook/{id}") 
	 * public String deleteBook(@PathVariable("id")
	 * int id, Model model) 
	 * { bookService.deleteBookById(id); 
	 * return
	 * "redirect:/available_books"; }
	 */
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
    	userService.deleteUser(id);
    	return "redirect:/available_users";
    }
}
    

