package com.github.sorabh86.uigo.admin.orders;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.sorabh86.uigo.constants.OrderStatus;
import com.github.sorabh86.uigo.entity.Order;

@Controller
@RequestMapping("admin/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("")
	public String list(Model m) {
		List<Order> orders = orderService.getOrderslist();
		m.addAttribute("orders", orders);
		return "admin/order/orders";
	}
	
	@GetMapping("/{id}/details")
	public String details(Model m, @PathVariable("id")Integer order_id) {
		Order order = orderService.getOrderById(order_id);
		
		if(!order.getIsVisited()) {
			order.setIsVisited(true);
			order = orderService.save(order);
		}
		
		m.addAttribute("order", order);
		return "admin/order/order-details";
	}
	
	@PostMapping("/{id}/status")
	public ResponseEntity changeStatus(
		@PathVariable("id")int id,
		@RequestParam("order_status")OrderStatus orderStatus,
		@RequestParam("delivery_days")Integer days
	) {
		
		Order order = orderService.getOrderById(id);
		order.setStatus(orderStatus);
		order.setExpected_delivery_days(days);
		if(orderStatus==OrderStatus.DELIVERED) {
			order.setDelivery_date(new Date());
		}
		
		orderService.save(order);
		
		Map<String, Object> map = new HashMap<>();
		map.put("status", orderStatus);
		map.put("message", "OK");
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id")int id) {
		return "redirect:/admin/users";
	}
}
