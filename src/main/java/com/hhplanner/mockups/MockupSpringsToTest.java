package com.hhplanner.mockups;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hhplanner.entities.model.Spring;

public class MockupSpringsToTest {

	public static List<Spring> createSpringsListToSaveTest() {
		List<Spring> projects = new ArrayList<>();
		projects.add(createSpringS1(0));
		projects.add(createSpringS2(0));
		projects.add(createSpringS3(0));
		return projects;
	}
	
	public static Spring createSpringS1(int id) {
		return new Spring(id, "S11", "Spring 11", 10);
	}
	public static Spring createSpringS2(int id) {
		return new Spring(id, "S2", "Spring 2", 15);
	}
	public static Spring createSpringS3(int id) {
		return new Spring(id, "S3", "Spring 3", 20);
	}
	
}
