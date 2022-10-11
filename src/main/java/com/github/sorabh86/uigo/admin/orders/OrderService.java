package com.github.sorabh86.uigo.admin.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.constants.OrderStatus;
import com.github.sorabh86.uigo.entity.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	public Order save(Order order) {
		order.setUserId(order.getAddress().getUserId());
		return orderRepo.save(order);
	}
	
	public Order getOrderById(Integer id) {
		return orderRepo.findById(id).get();
	}
	
	public List<Order> getOrderslist() {
		return orderRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	
	public List<Order> getOrdersByUserId(Integer user_id) {
		return orderRepo.findByUserId(user_id);
	}
	
	public List<Order> getOrdersByUserIdAndStatus(Integer userId, OrderStatus status) {
		return orderRepo.findByUserIdAndStatus(userId, status);
	}
	public List<Order> getOrdersByUserIdAndStatusNot(Integer userId, OrderStatus status) {
		return orderRepo.findByUserIdAndStatusNot(userId, status);
	}
	
	public Integer getAllOrderCount() {
		return orderRepo.getByTotalCount();
	}
	public Integer getNewOrderCount() {
		return orderRepo.getByIsVisited(false);
	}
	
}
