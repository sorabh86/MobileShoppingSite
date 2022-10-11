package com.github.sorabh86.uigo.order;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.github.sorabh86.uigo.admin.orders.OrderRepo;
import com.github.sorabh86.uigo.constants.OrderStatus;
import com.github.sorabh86.uigo.entity.Address;
import com.github.sorabh86.uigo.entity.Order;
import com.github.sorabh86.uigo.entity.OrderItem;
import com.github.sorabh86.uigo.entity.Phone;

@DataJpaTest(/* properties = {"spring.jpa.hibernate.ddl-auto=create-drop"} */)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestOrder {
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void createOrders() {
		OrderItem orderItem1 = new OrderItem();
		Phone phone1 = entityManager.find(Phone.class, 1);
		orderItem1.setPhone(phone1);
		orderItem1.setQuantity(getRandomNumber(1,6));
		orderItem1.setAmount(getRandomNumber(8000,20000));
		
		OrderItem orderItem2 = new OrderItem();
		Phone phone2 = entityManager.find(Phone.class, 2);
		orderItem2.setPhone(phone2);
		orderItem2.setQuantity(getRandomNumber(1,6));
		orderItem2.setAmount(getRandomNumber(8000,20000));
		
		Order order = new Order();
		order.setStatus(OrderStatus.REQUESTED);
		Address address = entityManager.find(Address.class, 2);
		order.setAddress(address);
		order.setMessage("Can you send it fast as urgent");
	
		order.addOrderItem(orderItem1);
		order.addOrderItem(orderItem2);
		
		orderRepo.save(order);
	}
	
	@Test
	public void getOrdersList() {
		List<Order> orders = orderRepo.findAll();
		orders.forEach(order->System.out.println(order));
	}
	
	@Test
	public void getOrderById() {
		Optional<Order> order = orderRepo.findById(1);
		System.out.println(order);
		
	}
	
	@Test
	public void updateExistingOrderWithUserId() {
		List<Order> orders = orderRepo.findAll();
		
		orders.forEach(order->{
			order.setUserId(order.getAddress().getUserId());
			orderRepo.save(order);
		});
	}
	
	@Test
	public void setVisitedFalse() {
		List<Order> orders = orderRepo.findAll();
		orders.forEach(order->{
			if(order.getStatus()==OrderStatus.REQUESTED) {
				order.setIsVisited(false);
			} else {
				order.setIsVisited(true);			
			}
			
			orderRepo.save(order);
		});
	}
	
	public int getRandomNumber(int start, int end) {
		return (int)(start+(Math.round(Math.random()*(end-start))));
	}
}
