package com.hhplanner.mockups;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.CapacityService;
import com.hhplanner.entities.service.FeatureService;
import com.hhplanner.entities.service.UserService;

@Service
public class MockupAsignmentsToTest {
	
	@Autowired
	private FeatureService featureService;
	@Autowired
	private UserService userService;
	@Autowired
	private CapacityService capacityService;
	
	public MockupAsignmentsToTest(FeatureService featureService,UserService userService, CapacityService capacityService ) {
		this.featureService = featureService;
		this.userService = userService;
		this.capacityService = capacityService;
	}
	
	public List<Asignment> createAsignmentsListToTest(int pid, int sid) {
		List<Asignment> asignment = new ArrayList<>();
		asignment.add(createAsignmentF1(pid,sid));
		asignment.add(createAsignmentF2(pid,sid));
		asignment.add(createAsignmentF3(pid,sid));
		return asignment;
	}

	public List<Asignment> createAsignmentsListToSaveTest(int pid, int sid) {
		List<Asignment> asignments = new ArrayList<>();
		asignments.add(createAsignmentF1(0,pid,sid));
		asignments.add(createAsignmentF2(0,pid,sid));
		asignments.add(createAsignmentF3(0,pid,sid));
		return asignments;
	}
	
	public Asignment createAsignmentF1(int pid, int sid) {
		return createAsignmentF1(1,pid,sid);
	}
	public Asignment createAsignmentF2(int pid, int sid) {
		return createAsignmentF2(2,pid,sid);
	}
	public Asignment createAsignmentF3(int pid, int sid) {
		return createAsignmentF3(3,pid,sid);
	}
	public Asignment createAsignmentF1(int id,int pid, int sid) {
		Feature featureInDB = this.featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF1(0),pid);
		User1 userInDB = this.userService.saveAndFlush(MockupUsersToTest.createUser1());
		this.capacityService.saveAndFlush(new Capacity(0, userInDB, 8), sid);
		return new Asignment(id,featureInDB,userInDB);
	}
	public Asignment createAsignmentF1noCapacity(int id,int pid) {
		Feature featureInDB = this.featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF1(0),pid);
		User1 userInDB = this.userService.saveAndFlush(MockupUsersToTest.createUser1());
		return new Asignment(id,featureInDB,userInDB);
	}
	public Asignment createAsignmentF2(int id,int pid, int sid) {
		Feature featureInDB = featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF2(0),pid);
		User1 userInDB = userService.saveAndFlush(MockupUsersToTest.createUser2());
		this.capacityService.saveAndFlush(new Capacity(0, userInDB, 8), sid);
		return new Asignment(id,featureInDB,userInDB);
	}
	public Asignment createAsignmentF2noCapacity(int id,int pid) {
		Feature featureInDB = featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF2(0),pid);
		User1 userInDB = userService.saveAndFlush(MockupUsersToTest.createUser2());
		return new Asignment(id,featureInDB,userInDB);
	}
	public Asignment createAsignmentF2(int id,User1 user, int pid) {
		Feature featureInDB = featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF2(0),pid);
		return new Asignment(id,featureInDB,user);
	}

	public Asignment createAsignmentF3(int id,int pid, int sid) {
		Feature featureInDB = featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF3(0),pid);
		User1 userInDB = userService.saveAndFlush(MockupUsersToTest.createUser3());
		this.capacityService.saveAndFlush(new Capacity(0, userInDB, 8), sid);
		return new Asignment(id,featureInDB,userInDB);
	}
	
	public void deleteAll() {
		this.capacityService.deleteAll();
		this.featureService.deleteAll();
		this.userService.deleteAll();
	}
	
	public static Date newDate(int year,int month, int day) {
		return new GregorianCalendar(year, month, day).getTime();
	}
}
