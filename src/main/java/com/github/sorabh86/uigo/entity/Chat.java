package com.github.sorabh86.uigo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.sorabh86.uigo.constants.ChatStatus;

@Entity
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String receiverUsername;
	
	private String message;
	
	@CreationTimestamp
	@DateTimeFormat
	private Date date;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private ChatStatus status;
	
	@ColumnDefault("false")
	public Boolean isVisited = false;
	
	public Chat() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceiverUsername() {
		return receiverUsername;
	}

	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ChatStatus getStatus() {
		return status;
	}

	public void setStatus(ChatStatus status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Chat [id=" + id + ", username=" + username + ", receiverUsername=" + receiverUsername + ", message="
				+ message + ", date=" + date + ", status=" + status + "]";
	}
	
}
