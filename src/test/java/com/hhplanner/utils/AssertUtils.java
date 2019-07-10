package com.hhplanner.utils;

import static org.junit.Assert.assertTrue;

import java.util.Objects;

import com.hhplanner.entities.model.Project;

public class AssertUtils {

	public static void assertObjectsEqual(Project p1, Project p2) {
		assertTrue("Expected "+ p1 + " but was " + p2 ,Objects.equals(p1.toString(), p2.toString()));
		
	}
	
}
