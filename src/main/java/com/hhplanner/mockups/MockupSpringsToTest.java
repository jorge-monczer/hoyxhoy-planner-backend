package com.hhplanner.mockups;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.hhplanner.entities.model.Spring;

public class MockupSpringsToTest {
	public static List<Spring> createSpringsListToTest() {
		List<Spring> projects = new ArrayList<>();
		projects.add(createSpringS1());
		projects.add(createSpringS2());
		projects.add(createSpringS3());
		return projects;
	}

	public static List<Spring> createSpringsListToSaveTest() {
		List<Spring> projects = new ArrayList<>();
		projects.add(createSpringS1(0));
		projects.add(createSpringS2(0));
		projects.add(createSpringS3(0));
		return projects;
	}
	
	public static Spring createSpringS1() {
		return createSpringS1(1);
	}
	public static Spring createSpringS2() {
		return createSpringS2(2);
	}
	public static Spring createSpringS3() {
		return createSpringS3(3);
	}
	public static Spring createSpringS1(int id) {
		return new Spring(id, "S1", "Spring 1", LocalDate.of(2019, 10, 1),LocalDate.of(2019, 10, 10), 10);
	}
	public static Spring createSpringS2(int id) {
		return new Spring(id, "S2", "Spring 2", LocalDate.of(2019, 10, 2),LocalDate.of(2019, 10, 17), 15);
	}
	public static Spring createSpringS3(int id) {
		return new Spring(id, "S3", "Spring 3", LocalDate.of(2019, 10, 3),LocalDate.of(2019, 10, 23), 20);
	}
	
	public static Date newDate(int year,int month, int day) {
		return new GregorianCalendar(year, month, day).getTime();
	}
}
