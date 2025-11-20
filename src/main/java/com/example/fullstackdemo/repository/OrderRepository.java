package com.example.fullstackdemo.repository;

import com.example.fullstackdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
