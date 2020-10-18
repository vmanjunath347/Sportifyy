package com.sportify.webapp.sportifyshoes.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;


@Entity
@Table(name="user")
public class users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;

	
	@Column(name="password")
	private String password;
	
	@Column(name="type")
	private String type;

	public users(long id, String email, String password, String name, String type) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.type = type;
	}
	
	public users() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
