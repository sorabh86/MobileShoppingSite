package com.github.sorabh86.uigo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String full_name;

	@Column(length=16)
	private String phone;
	
	@Column(length = 255, nullable = false)
	private String address_line_1;
	
	@Column(length = 255, nullable = false)
	private String address_line_2;
	
	@Column(length = 50, nullable = false)
	private String city;
	
	@Column(length = 50, nullable = false)
	private String state;
	
	@Column(length = 50, nullable = false)
	private String zip;
	
	@Column(length = 50, nullable = false)
	private String country;
	
//	@ManyToOne
//	private User user;
	@Column(name = "user_id")
	private Integer userId;

	public Address() {}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFull_name() {
		return full_name;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getAddress_line_1() {
		return address_line_1;
	}

	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", full_name=" + full_name + ", address_line_1=" + address_line_1
				+ ", address_line_2=" + address_line_2 + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", country=" + country + 
				", userId=" + userId +"]";
	}
	
}
