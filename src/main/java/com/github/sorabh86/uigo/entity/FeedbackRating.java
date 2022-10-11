package com.github.sorabh86.uigo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.sorabh86.uigo.constants.PhoneRates;

@Entity
@Table(name = "feedback_rating")
public class FeedbackRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Phone phone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	private PhoneRates rate;
	
	private String user_feedback_msg;
	
	@CreationTimestamp
	@DateTimeFormat
	@Column(updatable = false, nullable = false)
	private Date date;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean is_visted = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PhoneRates getRate() {
		return rate;
	}

	public void setRate(PhoneRates rate) {
		this.rate = rate;
	}

	public String getUser_feedback_msg() {
		return user_feedback_msg;
	}

	public void setUser_feedback_msg(String user_feedback_msg) {
		this.user_feedback_msg = user_feedback_msg;
	}

	public Boolean getIs_visted() {
		return is_visted;
	}

	public void setIs_visted(Boolean is_visted) {
		this.is_visted = is_visted;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "FeedbackRating [id=" + id + ", order=" + order + ", phone=" + phone + ", user=" + user + ", rate="
				+ rate + ", user_feedback_msg=" + user_feedback_msg + ", is_visted=" + is_visted + "]";
	}
}
