package com.hhplanner.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User1 {

	@Id
    @Column(name = "username", length = 20)
	private String username;
    @Column(name = "password", length = 60)
    private String password;
    @Column(name = "name", length = 60)
    private String name;
    @Column(name = "email", length = 60)
    private String email;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "mobile", length = 20)
    private String mobile;

    public User1() {
		super();
	}
    
    public User1(String username, String password, String name, String email, String phone, String mobile) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("<User> {")
				.append("'userName':").append("'").append(this.username).append("'").append(",")
				.append("'password':").append("'").append(this.password).append("'").append(",")
				.append("'name':").append("'").append(this.name).append("'").append(",")
				.append("'email':").append("'").append(this.email).append("'").append(",")
				.append("'phone':").append("'").append(this.phone).append("'").append(",")
				.append("'mobile':").append("'").append(this.mobile).append("'").append("}")
				.toString();
	}
	
}
