package com.github.sorabh86.uigo.entity;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.sorabh86.uigo.constants.OrderStatus;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer expected_delivery_days;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@DateTimeFormat
	private Date delivery_date;
	
	@CreationTimestamp
	@Column(updatable = false)
	@DateTimeFormat
	private Date created_date;
	
	@Column(length = 255)
	private String message;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Address address;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(length=60, name = "payment_method")
	private String paymentMethod;
	
	@OneToMany(
		cascade = CascadeType.ALL, 
		fetch = FetchType.EAGER
	)
	@JoinColumn(name = "order_id")
	private List<OrderItem> orderItems;
	
	@OneToMany(
		cascade = CascadeType.ALL, 
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "order_id")
	private List<Payment> payments;
	
	@OneToMany(
		cascade = CascadeType.ALL, 
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "order_id")
	private List<ReturnOrder> returnOrders;
	
	private Boolean isVisited = false;
	
	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<ReturnOrder> getReturnOrders() {
		return returnOrders;
	}

	public void setReturnOrders(List<ReturnOrder> returnOrders) {
		this.returnOrders = returnOrders;
	}

	public void addOrderItem(OrderItem orderItem) {
		if(this.orderItems==null) {
			this.orderItems = new ArrayList<>();
		}
		this.orderItems.add(orderItem);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getExpected_delivery_days() {
		return expected_delivery_days;
	}

	public void setExpected_delivery_days(Integer expected_delivery_days) {
		this.expected_delivery_days = expected_delivery_days;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Date getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
		public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Boolean getIsVisited() {
		return isVisited;
	}

	public void setIsVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	@Transient
	public float getGrandTotal() {
		float total = 0f;
		for(int i=0; i<orderItems.size(); i++) {
			total += orderItems.get(i).getAmount();
		}
		return total;
	}
	
	@Transient
	public boolean isReturnable() {
		if(delivery_date==null || status!=OrderStatus.DELIVERED) return false;
		
		// check if you can return this order.
		Instant deliverDate = delivery_date.toInstant();
		Instant returnDate = deliverDate.plus(Duration.ofDays(15));
		Instant todayDate = Instant.now();
		
		if(todayDate.isBefore(returnDate)) return true;
			
		
		return false;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", expected_delivery_days=" + expected_delivery_days + ", status=" + status
				+ ", delivery_date=" + delivery_date + ", created_date=" + created_date + ", message=" + message
				+ ", address=" + address + ", userId=" + userId + ", paymentMethod=" + paymentMethod + ", orderItems="
				+ orderItems + ", payments=" + payments + "]";
	}

}
