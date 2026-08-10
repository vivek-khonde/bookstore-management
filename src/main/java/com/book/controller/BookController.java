package com.book.controller;

import org.springframework.web.servlet.mvc.support.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.model.Book;
import com.book.model.MyBookList;
import com.book.model.User;
import com.book.service.BookService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.*;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private com.book.service.MyBookListService myBookService;

	@GetMapping("/book_register")
	public String bookRegister(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("page", "bookForm");

		return "bookRegister"; 
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("page", "home"); // only homepage gets this
		return "index";
	}

	@PostMapping("/saveBook") /// book_register
	public String submitBookRegisterForm(@ModelAttribute("book") Book book, RedirectAttributes redirectAttrs) {
		boolean status = bookService.saveBook(book);
		if (status) {
			redirectAttrs.addFlashAttribute("successMsg", "Book Added Successfully");
		} else {
			redirectAttrs.addFlashAttribute("errorMsg", "Something went wrong");
		}
		return "redirect:/available_books";
	}

	@GetMapping("/available_books")
	public String getAllBooks(Model model) {
		List<Book> list = bookService.getAllBooks();
		model.addAttribute("book", list);
		return "bookList";
	}
	
	@RequestMapping("/mylist/{id}")
	public String addToMyList(@PathVariable("id") int bookId, HttpSession session) {
	    // Get the logged-in user from session
	    User loggedInUser = (User) session.getAttribute("loggedInUser");

	    if (loggedInUser != null) {
	        // Fetch the book by its ID
	        Book book = bookService.getBookById(bookId);

	        // Create a new MyBookList entry for the logged-in user
	        MyBookList myBookList = new MyBookList();
	        myBookList.setName(book.getName());
	        myBookList.setAuthor(book.getAuthor());
	        myBookList.setPrice(book.getPrice());
	        myBookList.setDescription(book.getDescription());
	        myBookList.setStock(book.getStock()); // You might want to decrement stock here if applicable
	        myBookList.setUser(loggedInUser);  // Associate the book with the logged-in user

	        // Save the MyBookList entry to the user's collection
	        myBookService.saveMyBooks(myBookList);
	    }

	    // Redirect to MyBooks page where user can see their books
	    return "redirect:/my_books";
	}

	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "bookEdit";
	}

	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id, Model model) {
		bookService.deleteBookById(id);
		return "redirect:/available_books";
	}

}
