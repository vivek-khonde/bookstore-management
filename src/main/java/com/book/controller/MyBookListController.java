package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.model.Book;
import com.book.model.MyBookList;
import com.book.model.User;
import com.book.service.BookService;
import com.book.service.MyBookListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyBookListController {

	@Autowired
	private MyBookListService service;
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping("/my_books")
	public String getAllBooks(Model model, HttpSession session) {
	    User loggedInUser = (User) session.getAttribute("loggedInUser");

	    if (loggedInUser != null) {
	        // Fetch only books added by this user
	        List<MyBookList> userBooks = service.getMyBooksByUser(loggedInUser.getId());
	        model.addAttribute("book", userBooks);
	    } else {
	        // No logged-in user, show empty list
	        model.addAttribute("book", List.of());
	    }

	    return "myBooks";  
	}
	
	@GetMapping("/addMyBook")
	public String addMyBookForm(Model model, HttpSession session) {
	    MyBookList myBookList = new MyBookList();
	    User loggedInUser = (User) session.getAttribute("loggedInUser");
	    myBookList.setUser(loggedInUser);  // <- link book to user
	    model.addAttribute("myBookList", myBookList);
	    return "addMyBook";
	}


	@GetMapping("/editMyBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		MyBookList myBookList = service.getBookById(id);
		model.addAttribute("myBookList", myBookList);
		return "myBookEdit";
	}
	
	 // Save the edited book
    @PostMapping("/saveMyBooks")
    public String saveMyBook(@ModelAttribute("myBookList") MyBookList myBookList, HttpSession session) {
    	 User loggedInUser = (User) session.getAttribute("loggedInUser");
    	if (loggedInUser != null) {
            myBookList.setUser(loggedInUser); // Link the book to the currently logged-in user
            service.saveMyBooks(myBookList);
        }
        return "redirect:/my_books";
    }
	
	@RequestMapping("/deleteMyBook/{id}")
	public String deleteMyBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/my_books";
	}
}
