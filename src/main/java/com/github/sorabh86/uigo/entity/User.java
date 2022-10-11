package com.github.sorabh86.uigo.entity;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;

import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.constants.UserStatus;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 60, nullable = false, unique = true)
	private String username;
	
	@Column(length = 120, nullable = false)
	private String password;
	
	@Column(length = 60)
	private String full_name;

	@Column(length = 60, nullable = false)
	private String email;

	@Column(length = 16)
	private String phone_no;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private UserRoles role;

	@Column(length = 255, nullable = true)
	private String activation_key;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(length = 8)
	private UserStatus status; 

	@OneToMany(
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER
	)
	@JoinColumn(name = "user_id")
	private List<Address> addresses;
	
	public User() {}
	
	public User(String username, String password, 
			String full_name, String email, 
			String phone_no, UserRoles role,
			String activation_key, UserStatus status) {
		this.username = username;
		this.password = password;
		this.full_name = full_name;
		this.email = email;
		this.phone_no = phone_no;
		this.role = role;
		this.activation_key = activation_key;
		this.status = status;
	}
	
	public void addAddress(Address address) {
		if(addresses==null) {
			addresses = new ArrayList<>();
		}
		addresses.add(address);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFull_name() {
		return full_name;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public UserRoles getRole() {
		return role;
	}
	public void setRole(UserRoles role) {
		this.role = role;
	}
	public String getActivation_key() {
		return activation_key;
	}
	public void setActivation_key(String activation_key) {
		this.activation_key = activation_key;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", full_name=" + full_name
				+ ", email=" + email + ", phone_no=" + phone_no + ", role=" + role + ", activation_key="
				+ activation_key + ", status=" + status + ", addresses=" + addresses + "]";
	}
}
