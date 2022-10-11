package com.github.sorabh86.uigo.admin.orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.sorabh86.uigo.constants.OrderStatus;
import com.github.sorabh86.uigo.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
	
	public List<Order> findByUserId(Integer userId);
	public List<Order> findByUserIdAndStatus(Integer userId, OrderStatus status);
	
	public List<Order> findByUserIdAndStatusNot(Integer userId, OrderStatus status);
	
	@Query(value="SELECT COUNT(*) FROM orders",nativeQuery = true)
	public Integer getByTotalCount();
	
	@Query(value="SELECT COUNT(*) FROM orders WHERE is_visited=?",nativeQuery = true)
	public Integer getByIsVisited(Boolean isVisited);
}
