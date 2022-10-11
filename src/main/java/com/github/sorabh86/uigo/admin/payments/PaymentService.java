package com.github.sorabh86.uigo.admin.payments;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.entity.Payment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {
	
	public static String RAZORPAY_KEY = "rzp_test_nLIXAPQblFuAPV";
	public static String RAZORPAY_SECRET = "aCSDzmDy8ELRFtdOTUmaufzL";
	
	private static RazorpayClient client;
	
	@Autowired
	private PaymentRepo payRepo;
	
	public List<Payment> getPaymentList() {
		return payRepo.findAll();
	}
	
	public Payment getPayment(Integer id) {
		if(id==null || id==0) return new Payment();
		return payRepo.findById(id).get();
	}
	
	public Payment getPaymentByRazorOrderId(String razorOrderId) {
		return payRepo.findByRazorOrderId(razorOrderId);
	}
	
	public Payment savePayment(Payment payment) {
		return payRepo.save(payment);
	}
	
	public void deletePayment(Payment payment) {
		payRepo.delete(payment);
	}
	public void deletePaymentById(Integer id) {
		payRepo.deleteById(id);
	}
	
	public Order createRazorOrder(JSONObject json) throws RazorpayException {
		if(client==null)  client = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
		return client.Orders.create(json);
	}
	public List<Order> getRazorPaymentList() throws RazorpayException {
		if(client==null)  client = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
		return client.Orders.fetchAll();	
	}
	public Order getRazorPaymentById(String id) throws RazorpayException {
		if(client==null)  client = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
		return client.Orders.fetch(id);
	}
	public void getRazorPaymentById(String id, JSONObject json) throws RazorpayException {
		if(client==null)  client = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
		client.Orders.delete(id, json);
	}
}
