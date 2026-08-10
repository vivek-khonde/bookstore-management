package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.MyBookList;

public interface MyBookListRepository extends JpaRepository<MyBookList, Integer> {
	List<MyBookList> findByUserId(int userId);
	}
