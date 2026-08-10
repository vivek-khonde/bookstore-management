package com.book.service;

import com.book.model.Order;

import java.util.List;

public interface OrderService {

	void updateOrderStatus(Integer orderId, String status);
	
    Order createOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(int orderId);

    void updateOrderStatus(int orderId, String status);

    void deleteOrder(int orderId);
}
