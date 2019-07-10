package com.hhplanner.utils;

public class CollectionUtils {

	public static boolean isEmpty(Iterable<?> col ) {
		return !col.iterator().hasNext();
	}
	
}
