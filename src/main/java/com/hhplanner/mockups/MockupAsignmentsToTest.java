package com.hhplanner.mockups;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.FeatureService;
import com.hhplanner.entities.service.UserService;

@Service
public class MockupAsignmentsToTest {
	
	@Autowired
	private FeatureService featureService;
	@Autowired
	private UserService userService;
	
	public MockupAsignmentsToTest(FeatureService featureService,UserService userService ) {
		this.featureService = featureService;
		this.userService = userService;
	}
	
	public List<Asignment> createAsignmentsListToTest(int pid) {
		List<Asignment> asignment = new ArrayList<>();
		asignment.add(createAsignmentF1(pid));
		asignment.add(createAsignmentF2(pid));
		asignment.add(createAsignmentF3(pid));
		return asignment;
	}

	public List<Asignment> createAsignmentsListToSaveTest(int pid) {
		List<Asignment> asignments = new ArrayList<>();
		asignments.add(createAsignmentF1(0,pid));
		asignments.add(createAsignmentF2(0,pid));
		asignments.add(createAsignmentF3(0,pid));
		return asignments;
	}
	
	public Asignment createAsignmentF1(int pid) {
		return createAsignmentF1(1,pid);
	}
	public Asignment createAsignmentF2(int pid) {
		return createAsignmentF2(2,pid);
	}
	public Asignment createAsignmentF3(int pid) {
		return createAsignmentF3(3,pid);
	}
	public Asignment createAsignmentF1(int id,int pid) {
		Feature featureInDB = this.featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF1(0),pid);
		User1 userInDB = this.userService.saveAndFlush(MockupUsersToTest.createUser1());
		return new Asignment(id,featureInDB,userInDB);
	}
	public Asignment createAsignmentF2(int id,int pid) {
		Feature featureInDB = featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF2(0),pid);
		User1 userInDB = userService.saveAndFlush(MockupUsersToTest.createUser2());
		return new Asignment(id,featureInDB,userInDB);
	}
	public Asignment createAsignmentF3(int id,int pid) {
		Feature featureInDB = featureService.saveAndFlush(MockupFeaturesToTest.createFeatureF3(0),pid);
		User1 userInDB = userService.saveAndFlush(MockupUsersToTest.createUser3());
		return new Asignment(id,featureInDB,userInDB);
	}
	
	public void deleteAll() {
		this.featureService.deleteAll();
		this.userService.deleteAll();
	}
	
	public static Date newDate(int year,int month, int day) {
		return new GregorianCalendar(year, month, day).getTime();
	}
}
