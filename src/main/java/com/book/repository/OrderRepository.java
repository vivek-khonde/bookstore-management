package com.book.repository;

import com.book.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // You can add custom query methods if needed (e.g., find by user)
}
