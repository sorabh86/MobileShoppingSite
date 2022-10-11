package com.github.sorabh86.uigo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.sorabh86.uigo.constants.ReturnStatus;

@Entity
@Table(name = "return_order")
public class ReturnOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "return_reason")
	private String returnReason;
	
	@Enumerated(EnumType.STRING)
	private ReturnStatus status;
	
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


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String return_reason) {
		this.returnReason = return_reason;
	}

	public ReturnStatus getStatus() {
		return status;
	}

	public void setStatus(ReturnStatus status) {
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
		return "ReturnOrder [id=" + id + ", orderId=" + orderId + ", returnReason=" + returnReason + ", status="
				+ status + ", modify_date=" + modify_date + ", created_date=" + created_date + ", getId()=" + getId()
				+ ", getOrderId()=" + getOrderId() + ", getReturnReason()=" + getReturnReason() + ", getStatus()="
				+ getStatus() + ", getModify_date()=" + getModify_date() + ", getCreated_date()=" + getCreated_date()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
