package com.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.Book;
import com.book.repository.BookRepository;
import com.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;
	
	@Override
	public boolean saveBook(Book book) {
		 bookRepo.save(book);
		 try 
			{
				bookRepo.save(book);
				return true;
			} catch (Exception e) 
			{
				e.printStackTrace();
				return false;
			}
		
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public Book getBookById(int id) {
		return bookRepo.findById(id).get();
	}

	@Override
	public void deleteBookById(int id) {
		bookRepo.deleteById(id);
		
	}

}
