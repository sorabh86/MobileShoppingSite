package com.github.sorabh86.uigo.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sorabh86.uigo.admin.orders.OrderService;
import com.github.sorabh86.uigo.admin.users.MyHttpSessionBindingListener;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("")
	public String dashboard(Model m, HttpSession session) {
		
		MyHttpSessionBindingListener listener = 
				(MyHttpSessionBindingListener)session.getAttribute("user");
		
		m.addAttribute("newOrderCount", orderService.getNewOrderCount());
		m.addAttribute("totalOrders", orderService.getAllOrderCount());
		
		m.addAttribute("loggedInUsers", listener.getLoggedInUsers().getUsers().size());
		
		return "admin/dashboard";
	}
}
