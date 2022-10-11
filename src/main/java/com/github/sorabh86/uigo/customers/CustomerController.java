package com.github.sorabh86.uigo.customers;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sorabh86.uigo.admin.address.AddressService;
import com.github.sorabh86.uigo.admin.chats.ChatRepo;
import com.github.sorabh86.uigo.admin.feedbacks.FeedbackService;
import com.github.sorabh86.uigo.admin.orders.OrderService;
import com.github.sorabh86.uigo.admin.returns.ReturnService;
import com.github.sorabh86.uigo.admin.users.UserService;
import com.github.sorabh86.uigo.config.UIGOUserDetails;
import com.github.sorabh86.uigo.constants.ChatStatus;
import com.github.sorabh86.uigo.constants.OrderStatus;
import com.github.sorabh86.uigo.constants.PhoneRates;
import com.github.sorabh86.uigo.constants.ReturnStatus;
import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.entity.Address;
import com.github.sorabh86.uigo.entity.Chat;
import com.github.sorabh86.uigo.entity.FeedbackRating;
import com.github.sorabh86.uigo.entity.Order;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.ReturnOrder;
import com.github.sorabh86.uigo.entity.User;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private FeedbackService feedService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ReturnService returnSerivce;
	
	@Autowired
	private ChatRepo chatRepo;

	@GetMapping("")
	public String customer() {
		return "redirect:/customer/profile";
	}

	@PostMapping("/save")
	public String save(User user, RedirectAttributes ra) {
		
		User existingUser = userService.getUserByUsername(user.getUsername());
		if(existingUser!=null) {
			ra.addFlashAttribute("error", "Username already existed, please user different username");
			return "redirect:/register";
		}
		
		user.setUsername(user.getUsername().toLowerCase());
		user.setActivation_key(userService.getActivationKey());
		userService.save(user);

		ra.addFlashAttribute("message", "You are registered succesfully, please login.");
		
		return "redirect:/login";
	}

	@GetMapping("/profile")
	public String profile(Model m) {
		UIGOUserDetails userDetails = (UIGOUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		
//		m.addAttribute("message", "good job");
		m.addAttribute("customer", userDetails.getUser());
		return "customer/profile";
	}
	
	@PostMapping("/profile/password")
	public String profilePassword(
			Model m,
			@AuthenticationPrincipal UIGOUserDetails userdetails,
			RedirectAttributes redirectAttributes,
			@RequestParam("curpassword") String curPassword,
			@RequestParam("newpassword") String newPassword
	) {
		User user = userdetails.getUser();
		
		if(!passwordEncoder.matches(curPassword, user.getPassword())) {
			redirectAttributes.addFlashAttribute("message", "Current password is invalid!!");
			return "redirect:/customer/profile";
		}
		System.out.println(curPassword+" "+newPassword);
		System.out.println(user);
		
		user.setPassword(newPassword);
		userService.save(user);
		redirectAttributes.addFlashAttribute("message", "Your Password is Updated!!!");
		return "redirect:/customer/profile";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") String id) {
		return "customer/profile_form";
	}

	@GetMapping("/orders")
	public String ordersList(Model m, 
			@AuthenticationPrincipal UIGOUserDetails userDetails) {

		User user = userDetails.getUser();
		List<Order> orders = orderService.getOrdersByUserIdAndStatusNot(user.getId(), OrderStatus.DELIVERED);

		m.addAttribute("orders", orders);
		m.addAttribute("user", user);
		
		return "customer/orders";
	}

	@GetMapping("/orders/{id}")
	public String orderDetail() {
		return "customer/order_detail";
	}
	@GetMapping("/orders/{id}/delete")
	public String removeOrder(@RequestParam("id")Integer id,
			RedirectAttributes ra) {
		ra.addFlashAttribute("message", "Your order("+id+") has been removed!!!");
		return "redirect:/customer/orders";
	}

	@GetMapping("/invoices")
	public String downloadOrderInvoice(Model m, 
			@AuthenticationPrincipal UIGOUserDetails userDetails) {
		User user = userDetails.getUser();
		List<Order> orders = orderService.getOrdersByUserIdAndStatus(user.getId(), OrderStatus.DELIVERED);
		
		m.addAttribute("orders", orders);
		
		return "customer/invoices";
	}

	@GetMapping("/return")
	public String returnOrderItem(
			Model m,
			@AuthenticationPrincipal UIGOUserDetails userDetails) {
		
		User user = userDetails.getUser();
		List<Order> orders = orderService.getOrdersByUserIdAndStatus(user.getId(), OrderStatus.DELIVERED);
		
		m.addAttribute("orders", orders);
		return "customer/returnorder";
	}
	
	@PostMapping("/return/save")
	public String returnOrderSave(Model m, 
			RedirectAttributes ra,
			@RequestParam("returnReason")String returnReason,
			@RequestParam("status")ReturnStatus status,
			@RequestParam("orderId")Integer orderId
		) {
		
		ReturnOrder returnOrder = new ReturnOrder();
		returnOrder.setReturnReason(returnReason);
		returnOrder.setStatus(status);
		returnOrder.setOrderId(orderId);
			
		returnSerivce.save(returnOrder);
		ra.addFlashAttribute("message","Your request submitted!!!");
		return "redirect:/customer/return";
	}
	
	@GetMapping("/address")
	public String addressList(
			@AuthenticationPrincipal UIGOUserDetails userDetails, 
			Model m) {
		
		List<Address> addresses = addressService.getAddressByUserId(userDetails.getUser().getId());
		System.out.println(addresses);
		m.addAttribute("addresses", addresses);
		
		return "customer/address";
	}

	@GetMapping("/address/new")
	public String addAddress(Model m,
			@AuthenticationPrincipal UIGOUserDetails userDetails) {
		User user = userDetails.getUser();
		
		Address address = new Address();
		address.setFull_name(user.getFull_name());
		address.setUserId(user.getId());
		
		m.addAttribute("address", address);
		m.addAttribute("page_title", "Admin | Add New Address");
		
		return "customer/address_form";
	}

	@PostMapping("/address/save")
	public String saveAddress(
			Address address, 
			RedirectAttributes redirectAttribute
			) {
		
		addressService.save(address);
		redirectAttribute.addFlashAttribute("message", "Your Address has been saved");
		
		return "redirect:/customer/address";
	}

	@GetMapping("/address/{id}/edit")
	public String editAddress(Model m,
			@PathVariable("id")int id) {
		
		Address address = addressService.getAddress(id);
		m.addAttribute("address", address);
		System.out.println(address);
		
		m.addAttribute("page_title", "Admin | Edit Address ("+id+")");

		return "customer/address_form";
	}

	@GetMapping("/address/{id}/delete")
	public String deleteAddress() {
		return "customer/address";
	}

	@GetMapping("/feedback")
	public String feedback(Model m,
			@AuthenticationPrincipal UIGOUserDetails userDetails) {
		User user = userDetails.getUser();
		List<Order> orders = orderService.getOrdersByUserIdAndStatus(user.getId(), OrderStatus.DELIVERED);
		
		m.addAttribute("orders", orders);

		return "customer/feedback";
	}
	
	@PostMapping("/feedback/save")
	public String saveFeedback(
		@AuthenticationPrincipal UIGOUserDetails userDetails,
		@RequestParam(value = "id", required = false)Integer id,
		@RequestParam("phone_id")Integer phone_id,
		@RequestParam("order_id")Integer order_id,
		@RequestParam("rate") Integer rate,
		@RequestParam("feedback_msg")String feedback_msg,
		RedirectAttributes redirectAttributes
	) {
		
		User user = userDetails.getUser();
		Order order = orderService.getOrderById(order_id);
		Phone phone = order.getOrderItems().stream().filter(orderitem->
			orderitem.getPhone().getId()==phone_id).collect(Collectors.toList()).get(0)
				.getPhone();
		
		FeedbackRating feed1;
		
		if(id!=null) {
			feed1 = feedService.getFeedback(id);
		} else {
		 feed1 = new FeedbackRating();
		}
		switch (rate) {
			case 1: feed1.setRate(PhoneRates.One); break;
			case 2: feed1.setRate(PhoneRates.Two); break;
			case 3: feed1.setRate(PhoneRates.Three); break;
			case 4: feed1.setRate(PhoneRates.Four); break;
			case 5: feed1.setRate(PhoneRates.Five);
		};
		feed1.setOrder(order);
		feed1.setPhone(phone);
		feed1.setUser(user);
		feed1.setUser_feedback_msg(feedback_msg);
		
		feedService.save(feed1);
		
		redirectAttributes.addFlashAttribute("message", "Your feedback submitted");
		
		return "redirect:/customer/feedback";
	}
	@PostMapping("/feedback/{pid}/{oid}")
	@ResponseBody
	public String getFeedbackOfUserAndPhone(
			@AuthenticationPrincipal UIGOUserDetails ud,
			@PathVariable("pid")Integer phone_id,
			@PathVariable("oid")Integer order_id			
	) {
		User user = ud.getUser();
//		Phone phone = phoneService.getPhone(phone_id);
//		FeedbackRating feed = feedService.getFeebackByUserAndPhone(user, phone);
		FeedbackRating feed = feedService.getFeedbackByUidPidOid(user.getId(), phone_id, order_id);
		
		var obj = new JSONObject();
		obj.put("status", false);
		obj.put("message", "No Feedback found on this phone");
		
		if(feed!= null) {
			obj.put("id", feed.getId());
			obj.put("msg", feed.getUser_feedback_msg());
			switch(feed.getRate()) {
				case One: obj.put("rate", 1); break;
				case Two: obj.put("rate", 2); break;
				case Three: obj.put("rate", 3); break;
				case Four: obj.put("rate", 4); break;
				case Five: obj.put("rate", 5); break;				 
			}
			obj.put("status", true);
			obj.put("message", "User already submitted Feedback on this phone");
		}
		return obj.toString();
	}

	@GetMapping("/chats")
	public String chat(Model m,
			@AuthenticationPrincipal UIGOUserDetails userDetails) {
		User user = userDetails.getUser();
		List<User> users = userService.getUsersByRole(UserRoles.ROLE_ADMIN);
		List<String> allUsers = users.stream().map(u -> u.getUsername()).collect(Collectors.toList());
		System.out.println(allUsers);
		List<String> onlineUsers = chatRepo.findOnlineUsers();
		List<Chat> publicMessages = chatRepo.findByStatusAndReceiverUsername(ChatStatus.MESSAGE, null);
		
		m.addAttribute("user", user);
		m.addAttribute("allUsers", allUsers);
		m.addAttribute("onlineUsers", onlineUsers);
		m.addAttribute("publicMessages", publicMessages);
		
		return "customer/chats";
	}


}
