package com.hhplanner.mockups;

import static com.hhplanner.entities.service.LoginService.MASTER_PASS;
import static com.hhplanner.entities.service.LoginService.MASTER_USER;

import java.util.ArrayList;
import java.util.List;

import com.hhplanner.entities.model.User1;

public class MockupUsersToTest {

	public static List<User1> createUserListToSaveTest() {
		List<User1> users = new ArrayList<>();
		users.add(createUser1());
		users.add(createUser2());
		users.add(createUser3());
		return users;
	}
	
	public static User1 createUser1() {
		return new User1("user1", "pass1", "user one", "user1@mail.com", "4100-2000", "15-1000-2000");
	}
	public static User1 createUser2() {
		return new User1("user2", "pass2", "user two", "user2@mail.com", "4100-2000", "15-1000-2000");
	}
	public static User1 createUser3() {
		return new User1("user3", "pass3","user three", "user3@mail.com", "4100-2000", "15-1000-2000");
	}
	public static User1 createUserMasterHoy() {
		return new User1(MASTER_USER, MASTER_PASS,MASTER_USER,MASTER_USER+"mail.com",null,null);
	}
}
