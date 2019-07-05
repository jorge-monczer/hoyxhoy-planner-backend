package com.hhplanner.mockups;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hhplanner.entities.model.Project;

public class MockupsToTest {

	public static List<Project> createProjectListToTest() {
		List<Project> projects = new ArrayList<>();
		projects.add(createProjectTLMK());
		projects.add(createProjectTLMK2());
		projects.add(createProjectTLMK3());
		return projects;
	}

	public static Project createProjectTLMK() {
		return new Project(1, "TLMK", "Mejoras Telemarketer", newDate("01/10/2019"), 10);
	}
	public static Project createProjectTLMK2() {
		return new Project(2, "TLMK2", "Mejoras Telemarketer2", newDate("02/10/2019"), 15);
	}
	public static Project createProjectTLMK3() {
		return new Project(3, "TLMK3", "Mejoras Telemarketer3", newDate("03/10/2019"), 20);
	}
	public static Project createProjectTLMK4ToSave() {
		return new Project(0, "TLMK4", "Mejoras Telemarketer4", newDate("01/10/2019"), 10);
	}

	
	public static Date newDate(String ddMMyyyy ) {
		Date date = new Date();
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(ddMMyyyy);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
