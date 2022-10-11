package com.github.sorabh86.uigo.admin.returns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sorabh86.uigo.constants.ReturnStatus;
import com.github.sorabh86.uigo.entity.ReturnOrder;

@Controller
@RequestMapping("/admin/returns")
public class ReturnController {
	
	@Autowired
	private ReturnService retService;
	
	@GetMapping("")
	public String returnOrderList(Model m) {
		
		List<ReturnOrder> returnOrders = retService.getAllReturnOrders();
		System.out.println(returnOrders);
		m.addAttribute("returnOrders", returnOrders);
		
		return "admin/return/returns";
	}
	
	@PostMapping("/status")
	public String changeStatus(Model m,
			RedirectAttributes ra,
			@RequestParam("id")Integer id, 
			@RequestParam("returnStatus")ReturnStatus status) {
		
		ReturnOrder returnOrder = retService.getReturnOrderById(id);
		returnOrder.setStatus(status);
		retService.save(returnOrder);
		
		ra.addFlashAttribute("message", "Status Updated, Successfully");
		
		return "redirect:/admin/returns";
	}
}
