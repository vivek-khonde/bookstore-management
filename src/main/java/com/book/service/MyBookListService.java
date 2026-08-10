package com.book.service;

import java.util.List;
import com.book.model.MyBookList;

public interface MyBookListService {

    // Save or update a book for a user
    void saveMyBooks(MyBookList book);

    // Fetch all books for a specific user
    List<MyBookList> getMyBooksByUser(int userId);

    // Fetch a book by id
    MyBookList getBookById(int id);

    // Delete a book by id
    void deleteById(int id);
}
