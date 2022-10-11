package com.github.sorabh86.uigo.payment;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.github.sorabh86.uigo.admin.orders.OrderRepo;
import com.github.sorabh86.uigo.admin.payments.PaymentRepo;
import com.github.sorabh86.uigo.constants.PaymentStatus;
import com.github.sorabh86.uigo.entity.Payment;
import com.razorpay.Order;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestPayment {
	
	@Autowired
	private PaymentRepo payRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Test
	public void testUpdatePaymentStatus() {
		Payment payment = payRepo.findById(4).get();
		
		payment.setStatus(PaymentStatus.PENDING);
		payRepo.save(payment);
	}
	
	@Test
	public void updatePaymentAmount() {
		List<Payment> payments = payRepo.findAll();
		
		for(int i=0; i<payments.size(); i++) {
			var payment = payments.get(i);
			
			Integer order_id = payment.getOrder_id();
			var order = orderRepo.getById(order_id);
			
			payment.setAmount(order.getGrandTotal());
			
			payRepo.save(payment);
		}
	}
	
	@Test
	public void updateRazorPaymentId() {
		
	}
}
