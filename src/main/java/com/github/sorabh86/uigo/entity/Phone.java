package com.github.sorabh86.uigo.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Transient;

import com.github.sorabh86.uigo.constants.Constants;

@Entity
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 255, nullable = false)
	private String title;
	
	@Column(length = 1024, nullable = false)
	private String description;
	
	@Column(precision = 2, length = 10, nullable = false)
	private float price;
	
	@Column(length = 255, nullable = true)
	private String image;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Date publish_date;
	
	
	public Phone() {}

	public Phone(Integer id, String title, String description, float price) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		
		return Objects.equals(id, other.id);
	}
	
	@Transient
	public String getImagePath() {
		if(id==null || image==null || image.isEmpty())
			return "/images/thumbnail-phone.png";
		return "/"+Constants.PHONE_DIR+this.id+"/"+this.image;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", title=" + title + ", price=" + price + ", image=" + image + ", publish_date="
				+ publish_date + "]";
	}

	
}
