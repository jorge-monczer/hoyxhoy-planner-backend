package com.hhplanner.entities.exception;

public class EntityModelDuplicatedException extends BusinessException {

	private static final long serialVersionUID = 2758024782684305281L;
	public static final String UNIQUE_KEY_ON_PROJECT_CODE="PUBLIC.PROJECT(CODE)";
	public static final String UNIQUE_KEY_ON_PROJECT_NAME="PUBLIC.PROJECT(NAME)";
	public static final String DUPLICATE_PROJECT_CODE="Code Project Duplicated";
	public static final String DUPLICATE_PROJECT_NAME="Name Project Duplicated";
	
	public EntityModelDuplicatedException(String msg) {
		super(msg);
	}
		
	public static EntityModelDuplicatedException getInstance(String msg) {
		if (msg.contains(UNIQUE_KEY_ON_PROJECT_CODE)) {
			return new EntityModelDuplicatedException(DUPLICATE_PROJECT_CODE);
		} else 	if (msg.contains(UNIQUE_KEY_ON_PROJECT_NAME)) {
			return new EntityModelDuplicatedException(DUPLICATE_PROJECT_NAME);				
		} else {
			return new EntityModelDuplicatedException(msg);								
		}
		
	}
	
}
