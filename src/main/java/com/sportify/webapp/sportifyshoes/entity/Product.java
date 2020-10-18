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
	private long id;
	
	@Column(name="brand name")
	private String brand;
	
	@Column(name="size")
	private int size;
	
	@Column(name="quantity")
	private long quantity;
	
	@Column(name="gender")
	private String gender;
	
	public Product() {}

	public Product(long id, String brand, int size, long quantity, String gender) {
		super();
		this.id = id;
		this.brand = brand;
		this.size = size;
		this.quantity = quantity;
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
