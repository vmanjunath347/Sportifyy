package com.sportify.webapp.sportifyshoes.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;


@Entity 
@Table(name="Product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long productId;
	
	@Column(name="brandname")
	private String brand;
	
	@Column(name="size")
	private int size;
	
	@Column(name="quantity")
	private long quantity;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="price")
	private int price;
	
	public Product() {}

	public Product(long productId, String brand, int size, long quantity, String gender,int price) {
		super();
		this.productId = productId;
		this.brand = brand;
		this.size = size;
		this.quantity = quantity;
		this.gender = gender;
		this.price=price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price=price; 
	}

	public long getId() {
		return productId;
	}

	public void setId(long id) {
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
	
}
