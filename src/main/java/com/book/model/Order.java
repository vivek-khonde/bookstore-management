package com.book.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne  // many orders belong to one user
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Remove CascadeType.ALL to prevent cascading deletes on books
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Keep persist and merge, but NOT remove
    @JoinColumn(name = "order_id") // Ensure the foreign key is handled correctly
    private List<MyBookList> books; // one order many book list entries

    private double totalPrice;

    private String orderStatus; // e.g., "Pending", "Shipped", "Completed"

    private Date orderDate;
    
    @Column(name = "shipping_option")
    private String shippingOption;

    public String getShippingOption() {
        return shippingOption;
    }

    public void setShippingOption(String shippingOption) {
        this.shippingOption = shippingOption;
    }


    public Order() {
        // Default constructor
    }

    // Constructor for placing an order
    public Order(User user, List<MyBookList> books, double totalPrice) {
        this.user = user;
        this.books = books;
        this.totalPrice = totalPrice;
        this.orderStatus = "Pending";
        this.orderDate = new Date();
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MyBookList> getBooks() {
        return books;
    }

    public void setBooks(List<MyBookList> books) {
        this.books = books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

	
}
