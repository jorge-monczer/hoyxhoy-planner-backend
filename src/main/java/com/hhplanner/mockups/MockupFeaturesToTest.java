package com.hhplanner.mockups;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.hhplanner.entities.model.Feature;

public class MockupFeaturesToTest {
	public static List<Feature> createFeaturesListToTest() {
		List<Feature> projects = new ArrayList<>();
		projects.add(createFeatureF1());
		projects.add(createFeatureF2());
		projects.add(createFeatureF3());
		return projects;
	}

	public static List<Feature> createFeaturesListToSaveTest() {
		List<Feature> projects = new ArrayList<>();
		projects.add(createFeatureF1(0));
		projects.add(createFeatureF2(0));
		projects.add(createFeatureF3(0));
		return projects;
	}
	
	public static Feature createFeatureF1() {
		return createFeatureF1(1);
	}
	public static Feature createFeatureF2() {
		return createFeatureF2(2);
	}
	public static Feature createFeatureF3() {
		return createFeatureF3(3);
	}
	public static Feature createFeatureF1(int id) {
		return new Feature(id, "F1", "Feature 1", LocalDate.of(2019, 10, 1),40);
	}
	public static Feature createFeatureF2(int id) {
		return new Feature(id, "F2", "Feature 2", LocalDate.of(2019, 10, 2),52);
	}
	public static Feature createFeatureF3(int id) {
		return new Feature(id, "F3", "Feature 3", LocalDate.of(2019, 10, 3),24);
	}
	
	public static Date newDate(int year,int month, int day) {
		return new GregorianCalendar(year, month, day).getTime();
	}
}
