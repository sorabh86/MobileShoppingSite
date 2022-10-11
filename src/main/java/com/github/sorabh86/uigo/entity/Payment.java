package com.github.sorabh86.uigo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.sorabh86.uigo.constants.PaymentStatus;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer order_id;
	
	private float amount;
	
	@Column(name = "razor_order_id", unique = true)
	private String razorOrderId;
	
	
	@Column(name = "razor_payment_id", unique = true)
	private String razorPaymentId;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PaymentStatus status;
	
	@UpdateTimestamp
	@DateTimeFormat
	private Date modify_date;
	
	@CreationTimestamp
	@DateTimeFormat
	@Column(updatable = false)
	private Date created_date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getRazorOrderId() {
		return razorOrderId;
	}

	public void setRazorOrderId(String razorOrderId) {
		this.razorOrderId = razorOrderId;
	}

	public String getRazorPaymentId() {
		return razorPaymentId;
	}

	public void setRazorPaymentId(String razorPaymentId) {
		this.razorPaymentId = razorPaymentId;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	@Override
	public String toString() {
		return "Payment [id=" + id + ", order_id=" + order_id + ", razorOrderId=" + razorOrderId + ", razorPaymentId="
				+ razorPaymentId + ", status=" + status + ", modify_date=" + modify_date + ", created_date="
				+ created_date + "]";
	}
	
}
