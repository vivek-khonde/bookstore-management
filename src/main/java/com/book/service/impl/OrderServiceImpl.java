package com.book.service.impl;

import com.book.model.Order;
import com.book.repository.OrderRepository;
import com.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null); // Return null if order not found
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.ifPresent(o -> {
            o.setOrderStatus(status);
            orderRepository.save(o);
        });
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }

	@Override
	public void updateOrderStatus(Integer orderId, String status) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(()-> new RuntimeException("Order not found"));
		
		order.setOrderStatus(status);
		orderRepository.save(order);
		
	}
}
