package com.hhplanner.mockups;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.hhplanner.entities.model.Project;

public class MockupProjectsToTest {
	public static List<Project> createProjectListToTest() {
		List<Project> projects = new ArrayList<>();
		projects.add(createProjectTLMK());
		projects.add(createProjectTLMK2());
		projects.add(createProjectTLMK3());
		return projects;
	}

	public static List<Project> createProjectListToSaveTest() {
		List<Project> projects = new ArrayList<>();
		projects.add(createProjectTLMK(0));
		projects.add(createProjectTLMK2(0));
		projects.add(createProjectTLMK3(0));
		return projects;
	}
	
	public static Project createProjectTLMK() {
		return createProjectTLMK(1);
	}
	public static Project createProjectTLMK2() {
		return createProjectTLMK2(2);
	}
	public static Project createProjectTLMK3() {
		return createProjectTLMK3(3);
	}
	public static Project createProjectTLMK(int id) {
		return new Project(id, "TLMK", "Mejoras Telemarketer", LocalDate.of(2019, 10, 1), 10);
	}
	public static Project createProjectTLMK2(int id) {
		return new Project(id, "TLMK2", "Mejoras Telemarketer2", LocalDate.of(2019, 10, 2), 15);
	}
	public static Project createProjectTLMK3(int id) {
		return new Project(id, "TLMK3", "Mejoras Telemarketer3", LocalDate.of(2019, 10, 3), 20);
	}
	
	public static Date newDate(int year,int month, int day) {
		return new GregorianCalendar(year, month, day).getTime();
	}
}
