package com.github.sorabh86.uigo.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sorabh86.uigo.admin.address.AddressService;
import com.github.sorabh86.uigo.admin.orders.OrderService;
import com.github.sorabh86.uigo.admin.payments.PaymentService;
import com.github.sorabh86.uigo.admin.phones.PhoneService;
import com.github.sorabh86.uigo.admin.users.UserService;
import com.github.sorabh86.uigo.config.UIGOUserDetails;
import com.github.sorabh86.uigo.constants.OrderStatus;
import com.github.sorabh86.uigo.constants.PaymentStatus;
import com.github.sorabh86.uigo.entity.Address;
import com.github.sorabh86.uigo.entity.CartItem;
import com.github.sorabh86.uigo.entity.Order;
import com.github.sorabh86.uigo.entity.OrderItem;
import com.github.sorabh86.uigo.entity.Payment;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.User;
import com.razorpay.RazorpayException;

@Controller
public class CartController {

	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired 
	private OrderService orderService;
	
	@Autowired
	private PaymentService payService;
	
	@PostMapping("/addtocart")
	public String addToCart(
			@RequestParam("redirect") String redirectUrl, 
			@RequestParam("phone_id")Integer phone_id, 
			@RequestParam("quantity")Integer quantity,
			@AuthenticationPrincipal UIGOUserDetails userDetails,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request
	) {
		
		User user = null;
		if(userDetails!=null) {
			String username = userDetails.getUsername();
			user = userService.getUserByUsername(username);
			cartService.addPhone(phone_id, quantity, user);
		} else {
			Phone phone = this.phoneService.getPhone(phone_id);
			CartItem newItem = new CartItem();
			newItem.setUser(user);
			newItem.setPhone(phone);
			newItem.setQuantity(quantity);
			
			cartService.addToCart(request.getSession(), newItem);
		}
		redirectAttributes.addFlashAttribute("message", "Item added successfully, go to <a href=\"/cart\">cart</a>.");
		
		return "redirect:"+redirectUrl;
	}
	@GetMapping("/removetocart/{pid}")
	public String removeToCart(
			@PathVariable("pid")Integer phone_id,
			@AuthenticationPrincipal UIGOUserDetails userDetails,
			HttpServletRequest request) {
		
		if(userDetails==null) {
			
		} else {
			cartService.removePhone(phone_id);			
		}
		
		return "redirect:/cart";
	}
	
	@GetMapping("/cart")
	public String cartPage(Model m, 
			@AuthenticationPrincipal UIGOUserDetails userDetails, 
			HttpServletRequest request) {
		List<CartItem> cartItems;
		List<Address> addresses;
		
		if(userDetails!=null) {
			String username = userDetails.getUsername();
			User user = userService.getUserByUsername(username);
			cartItems = cartService.getCartItemsByUser(user);
			addresses = user.getAddresses();
		} else {
			cartItems = cartService.getSessionCartItems(request.getSession());
			addresses = new ArrayList<>();
		}
		
		float totalPrice = 0f;
		for(int i=0; i<cartItems.size(); i++) {
			CartItem item = cartItems.get(i);
			totalPrice += (item.getPhone().getPrice()*item.getQuantity());
		}
		
		m.addAttribute("page", "cart");
		m.addAttribute("cartItems", cartItems);
		m.addAttribute("totalPrice", Math.round(totalPrice));
		m.addAttribute("addresses",addresses);
		
		return "cart";
	}
	
	@PostMapping("/cart/order")
	@ResponseBody /* This will change behavior of method to return string not matched template  */
	public String createOrder(
		@AuthenticationPrincipal UIGOUserDetails userDetails,
		@RequestBody Map<String, Object> data
	) throws RazorpayException {

		User user = userDetails.getUser();
		String paymentMethod = (String)data.get("payment-method");
		Integer address_id = Integer.parseInt(data.get("address_id").toString());
		String message = (String)data.get("message");
		Address address = addressService.getAddress(address_id);
		
		// check for address
		if(address_id==0) {
			address.setFull_name((String)data.get("full_name"));
			address.setAddress_line_1((String)data.get("address_line_1"));
			address.setAddress_line_2((String)data.get("address_line_2"));
			address.setPhone((String)data.get("phone"));
			address.setCity((String)data.get("city"));
			address.setState((String)data.get("state"));
			address.setCountry((String)data.get("country"));
			address.setZip((String)data.get("zip"));
			address.setUserId(userDetails.getUser().getId());
			
			address = addressService.save(address);
		}
		
		// Generate Order receipt
		Order newOrder = new Order();
		newOrder.setStatus(OrderStatus.REQUESTED);
		newOrder.setPaymentMethod(paymentMethod);
		newOrder.setAddress(address);
		newOrder.setMessage(message);
		
		newOrder.setOrderItems(this.getOrderItemsFromRequest(data));
		Order savedOrder = orderService.save(newOrder);
		
		// Empty cart saved to database
		cartService.emptyCart(user);
		
		return razorPaymentIntegration(newOrder.getGrandTotal(), savedOrder.getId(), user.getEmail(), address);
	}
	
	@PostMapping("/cart/payment")
	@ResponseBody
	private String createRazorOrderAndPayment(@RequestParam("id")Integer orderId,
			@RequestParam("amount") float amount,
			@AuthenticationPrincipal UIGOUserDetails userDetails) throws RazorpayException {
		User user = userDetails.getUser();
		Order order = orderService.getOrderById(orderId);
		
		return razorPaymentIntegration(amount, orderId, user.getEmail(), order.getAddress());
	}
	
	// razorpay payment integration
	private String razorPaymentIntegration(float amount, Integer order_id, String email, Address address) throws RazorpayException {
		
		JSONObject ob = new JSONObject();
		ob.put("amount", amount*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_"+order_id);
		
		// creating new order on razorpay server
		com.razorpay.Order order = payService.createRazorOrder(ob);
		System.out.println("RAZOR_ORDER: "+order);
			
		// SAVE CREATED ORDER TO LOCAL DATABASE
		Payment payment = new Payment();
		payment.setOrder_id(order_id);
		payment.setRazorOrderId(order.get("id"));
		payment.setStatus(PaymentStatus.PENDING);
		payment.setAmount(amount);
		payService.savePayment(payment);
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("username", address.getFull_name());
		returnObj.put("email", email);
		returnObj.put("phone", address.getPhone());
		returnObj.put("id", order.get("id").toString());
		returnObj.put("amount", Float.parseFloat(order.get("amount").toString()));
		
		return returnObj.toString();
	}
	
	@PostMapping("/cart/payment/update")
	@ResponseBody
	public String updatePaymentStatus(
			@RequestParam("razorOrderId") String razorOrderId,
			@RequestParam(name = "razorPaymentId", required=false) String razorPaymentId,
			@RequestParam("status") PaymentStatus status,
			@AuthenticationPrincipal UIGOUserDetails userDetails
	) {
		JSONObject json = new JSONObject();
		
		if(razorPaymentId!=null && !razorPaymentId.isEmpty()) {
			Payment payment = payService.getPaymentByRazorOrderId(razorOrderId);
			if(payment!=null) {
				json.put("paymentId", payment.getId());
				json.put("orderId", payment.getOrder_id());
				payment.setStatus(status);
				if(razorPaymentId!=null) 
					payment.setRazorPaymentId(razorPaymentId);
				payService.savePayment(payment);
			}			
		}
		
		json.put("status", status.name());
		json.put("razorOrderId", razorOrderId);
		
		return json.toString();
	}
	
	@PostMapping("/cart/update")
	public ResponseEntity create(@RequestParam("item_id")Integer item_id, 
			@RequestParam("quantity")Integer quantity) {
		CartItem cartItem = cartService.getCartItemById(item_id);
		cartItem.setQuantity(quantity);
		cartService.save(cartItem);
		return ResponseEntity.ok("OK");
	}
	
	@PostMapping("/checkout")
	public String checkout(
			@AuthenticationPrincipal UIGOUserDetails userDetails,
			@RequestParam Map<String, Object> data,
			RedirectAttributes redirectAttributes
	) {
		User user = userDetails.getUser();
		String paymentMethod = data.get("payment-method").toString();
		Integer address_id = Integer.parseInt(data.get("address_id").toString());
		String message = data.get("message").toString();
		Address address = addressService.getAddress(address_id);
		
		if(address_id==0) {
			address.setFull_name((String)data.get("full_name").toString());
			address.setAddress_line_1((String)data.get("address_line_1").toString());
			address.setAddress_line_2((String)data.get("address_line_2").toString());
			address.setPhone((String)data.get("phone").toString());
			address.setCity((String)data.get("city").toString());
			address.setState((String)data.get("state").toString());
			address.setCountry((String)data.get("country").toString());
			address.setZip((String)data.get("zip").toString());
			
			address.setUserId(userDetails.getUser().getId());
			
			address = addressService.save(address);
		}
		
		Order newOrder = new Order();
		newOrder.setStatus(OrderStatus.REQUESTED);
		newOrder.setPaymentMethod(paymentMethod);
		newOrder.setAddress(address);
		newOrder.setMessage(message);
		
		// GET CARTITEMS
		List<OrderItem> orderItems = this.getOrderItemsFromRequest(data);
		newOrder.setOrderItems(orderItems);
		
		System.out.println(newOrder);
		
		Order savedOrder = orderService.save(newOrder);
		
		// EMPTY CART
		cartService.emptyCart(user);
		
		redirectAttributes.addFlashAttribute("message", "Order Receipt is Created!!");
		
		// check for payment options
		if(paymentMethod=="COD") {
			// TODO: generate order straight;
			
		} else if(paymentMethod=="OP") {
			// TODO: implement online payment system like payTM, payu, etc.
		}

		return "redirect:/orderstatus/"+savedOrder.getId();
	}
	
	private List<OrderItem> getOrderItemsFromRequest(Map<String, Object> data) {
		List<OrderItem> itemList = new ArrayList<>();
		int count = 1;
		while(data.get("cartItems["+count+"].item_id")!=null) {
			int cart_item_id = Integer.parseInt(data.get("cartItems["+count+"].item_id").toString());
			int phone_id = Integer.parseInt(data.get("cartItems["+count+"].phone_id").toString());
			int quantity = Integer.parseInt(data.get("cartItems["+count+"].quantity").toString());
			float amount = Float.parseFloat(data.get("cartItems["+count+"].amount").toString());
			
			OrderItem item = new OrderItem();
			item.setPhone(phoneService.getPhone(phone_id));
			item.setAmount(amount);
			item.setQuantity(quantity);
			itemList.add(item);
			
			count++;
		}
		return itemList;
	}
	
	@GetMapping(value={"/orderstatus/{order_id}","/orderstatus/{order_id}/{payment_id}"})
	public String orderStatus(
			Model m,
			@PathVariable("order_id")Integer order_id,
			@PathVariable(name = "payment_id", required = false)Integer payment_id,
			@ModelAttribute("message")String message) {
		
		Order order = orderService.getOrderById(order_id);
		PaymentStatus payStatus = PaymentStatus.PENDING;
		List<OrderItem> orderItems = order.getOrderItems();
		float totalPrice = 0f;
		for(int i=0; i<orderItems.size(); i++) {
			OrderItem item = orderItems.get(i);
			totalPrice += (item.getAmount()*item.getQuantity());
		}
		
		if(payment_id!=null) {
			Payment payment = payService.getPayment(payment_id);
			System.out.println(payment);
			if(order.getId()==payment.getOrder_id() && payment.getId()!=null) {
				payStatus = payment.getStatus();
				m.addAttribute("payment", payment);
			}
		}
		
		m.addAttribute("message", message);
		m.addAttribute("payStatus", payStatus);
		m.addAttribute("order", order);
		m.addAttribute("totalPrice", totalPrice);
		
		return "orderstatus";
	}
	
	
}
