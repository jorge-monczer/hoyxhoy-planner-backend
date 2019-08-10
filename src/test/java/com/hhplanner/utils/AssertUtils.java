package com.hhplanner.utils;

import static org.junit.Assert.assertTrue;

import java.util.Objects;

public class AssertUtils {

	public static void assertObjectsEqual(Object p1, Object p2) {
		assertTrue("Expected "+ p1 + " but was " + p2 ,Objects.equals(p1.toString(), p2.toString()));
		
	}
	
}
