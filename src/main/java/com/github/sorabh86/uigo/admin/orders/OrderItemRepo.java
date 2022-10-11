package com.github.sorabh86.uigo.admin.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sorabh86.uigo.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
	
}
