package com.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.Book;
import com.book.model.MyBookList;
import com.book.repository.MyBookListRepository;
import com.book.service.MyBookListService;

@Service
public class MyBookListServiceImpl implements MyBookListService {

    @Autowired
    private MyBookListRepository myBookListRepo;

	@Override
	public void saveMyBooks(MyBookList book) {
		myBookListRepo.save(book);
		
	}

	@Override
	public List<MyBookList> getMyBooksByUser(int userId) {
		return myBookListRepo.findByUserId(userId);
	}

	@Override
	public MyBookList getBookById(int id) {
		 return myBookListRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteById(int id) {
		myBookListRepo.deleteById(id);
		
	}

	
    
}
