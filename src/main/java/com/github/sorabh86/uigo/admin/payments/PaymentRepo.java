package com.github.sorabh86.uigo.admin.payments;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sorabh86.uigo.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	public Payment findByRazorOrderId(String razorPaymentId);
}
