package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.Book;


public interface BookService {
	
	public boolean saveBook(Book book);
	
	public List<Book> getAllBooks();
	
	public Book getBookById(int id);
	
	public void deleteBookById(int id);
}
