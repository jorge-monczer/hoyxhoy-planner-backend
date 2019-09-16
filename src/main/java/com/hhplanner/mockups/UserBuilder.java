package com.hhplanner.mockups;

import static com.hhplanner.entities.service.LoginService.MASTER_PASS;
import static com.hhplanner.entities.service.LoginService.MASTER_USER;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.UserService;

public class UserBuilder {
	private User1 user;

	private UserService service;
	
	public UserBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(UserService.class);
	}
	
	public UserBuilder buildU1() {
		this.user = new User1("user1", "pass1", "user one", "user1@mail.com", "4100-2000", "15-1000-2000");
		return this;
	}

	public UserBuilder buildU2() {
		this.user = new User1("user2", "pass2", "user two", "user2@mail.com", "4100-2000", "15-1000-2000");
		return this;
	}
	
	public UserBuilder buildU3() {
		this.user = new User1("user3", "pass3","user three", "user3@mail.com", "4100-2000", "15-1000-2000");
		return this;
	}

	public UserBuilder buildUserMasterHoy() {
		this.user = new User1(MASTER_USER, MASTER_PASS,MASTER_USER,MASTER_USER+"mail.com",null,null);
		return this;
	}
	
	public User1 getUser() {
		if (this.user == null) {
			throw new BusinessException("Must build the user");
		}
		return this.user;
	}

	public UserBuilder save() {
		this.service.saveAndFlush(this.getUser());
		return this;
	}
	
	public void deleteAll() {
		this.service.deleteAll();
	}
	
}
