package com.hhplanner.mockups;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.UserService;

@Service
public class MockupCapacitiesToTest {
	
	@Autowired
	private UserService userService;
	
	public MockupCapacitiesToTest(UserService userService ) {
		this.userService = userService;
	}
	
	public List<Capacity> createCapacitiesListToTest() {
		List<Capacity> capacity = new ArrayList<>();
		capacity.add(createCapacityUser1());
		capacity.add(createCapacityUser2());
		capacity.add(createCapacityUser3());
		return capacity;
	}

	public List<Capacity> createCapacitiesListToSaveTest() {
		List<Capacity> capacities = new ArrayList<>();
		capacities.add(createCapacityUser1(0));
		capacities.add(createCapacityUser2(0));
		capacities.add(createCapacityUser3(0));
		return capacities;
	}
	
	public Capacity createCapacityUser1() {
		return createCapacityUser1(1);
	}
	public Capacity createCapacityUser2() {
		return createCapacityUser2(2);
	}
	public Capacity createCapacityUser3() {
		return createCapacityUser3(3);
	}
	public Capacity createCapacityUser1(int id) {
		User1 userInDB = this.userService.saveAndFlush(MockupUsersToTest.createUser1());
		return new Capacity(id,userInDB,8);
	}
	public Capacity createCapacityUser2(int id) {
		User1 userInDB = userService.saveAndFlush(MockupUsersToTest.createUser2());
		return new Capacity(id,userInDB,6);
	}
	public Capacity createCapacityUser3(int id) {
		User1 userInDB = userService.saveAndFlush(MockupUsersToTest.createUser3());
		return new Capacity(id,userInDB,4);
	}
	
	public void deleteAll() {
		this.userService.deleteAll();
	}
	
	public static Date newDate(int year,int month, int day) {
		return new GregorianCalendar(year, month, day).getTime();
	}
}
