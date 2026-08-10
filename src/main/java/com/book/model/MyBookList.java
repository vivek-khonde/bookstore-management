package com.book.model;

import jakarta.persistence.*;

@Entity
@Table(name = "my_book_list")
public class MyBookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String author;
    private int price;
    private String description;
    private int stock;

    // A book belongs to one user
    @ManyToOne
    @JoinColumn(name = "user_id") // Foreign key for User
    private User user;

    public MyBookList() {
        super();
    }

    public MyBookList(int id, String name, String author, int price, String description, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
