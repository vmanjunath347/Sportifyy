package com.sportify.webapp.sportifyshoes.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PurchaceDetails")
public class PurchaceDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="userid")
	private long userId;
	
	@Column(name="useremail")
	private String userEmail;
	
	@Column(name="productid")
	private long productId;
	
	@Column(name="brandname")
	private String brand;
	
	@Column(name="size")
	private int size;
	
	@Column(name="quantity")
	private long quantity;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="date")
	private Date date;

	public PurchaceDetails() {}
	
	public PurchaceDetails(long id, long userId, String userEmail, long productId, String brand, int size,
			long quantity, String gender, Date date) {
		super();
		this.id = id;
		this.userId = userId;
		this.userEmail = userEmail;
		this.productId = productId;
		this.brand = brand;
		this.size = size;
		this.quantity = quantity;
		this.gender = gender;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
