package com.github.sorabh86.uigo.admin.payments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService payService;
	
	@GetMapping("")
	public String listUsers(Model m) {
		m.addAttribute("payments", payService.getPaymentList());
		return "admin/payment/payment";
	}
	
}
