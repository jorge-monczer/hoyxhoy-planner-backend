package com.hhplanner.entities.exception;

public class BusinessExceptionFactory {
	public static String PROJECT_NOT_FOUND = "Project not found"; 
	public static String PROJECT_DUPLICATED = "Project duplicated"; 
	public static String SPRING_NOT_FOUND = "Spring not found"; 
	public static String SPRING_DUPLICATED = "Spring duplicated"; 
	public static String FEATURE_NOT_FOUND = "Feature not found"; 
	public static String FEATURE_DUPLICATED = "Feature duplicated"; 
	public static String USER_NOT_FOUND = "User not found"; 
	public static String PASSWORD_INVALID = "Invalid Password"; 
	public static String USER_ALREADY_EXISTS = "User already exists"; 
	public static String CAPACITY_NOT_FOUND = "Capacity not found"; 
	public static String CAPACITY_DUPLICATED = "Capacity duplicated"; 
	public static String FEATURE_NOT_ASIGNED = "Feature not asigned"; 
	public static String FEATURE_ALREADY_ASIGNED = "Feature already asigned"; 
	public static String USER_CAPACITY_INSUFFICIENT = "Insufficient user capacity"; 
	public static String SPRING_DAYS_INSUFFICIENT = "Insufficient springs days because of features asigned"; 

	public static BusinessException businessException(String message) {
		return new BusinessException(message);
	}
	
	public static EntityModelNotFoundException projectNotFoundException() {
		return new EntityModelNotFoundException(PROJECT_NOT_FOUND);
	}
	
	public static EntityModelDuplicatedException projectDuplicatedException() {
		return new EntityModelDuplicatedException(PROJECT_DUPLICATED);
	}

	public static EntityModelNotFoundException springNotFoundException() {
		return new EntityModelNotFoundException(SPRING_NOT_FOUND);
	}
	
	public static EntityModelDuplicatedException springDuplicatedException() {
		return new EntityModelDuplicatedException(SPRING_DUPLICATED);
	}
	
	public static EntityModelNotFoundException featureNotFoundException() {
		return new EntityModelNotFoundException(FEATURE_NOT_FOUND);
	}
	
	public static EntityModelDuplicatedException featureDuplicatedException() {
		return new EntityModelDuplicatedException(FEATURE_DUPLICATED);
	}

	public static EntityModelNotFoundException userNotFoundException() {
		return new EntityModelNotFoundException(USER_NOT_FOUND);
	}
	
	public static EntityModelNotFoundException passwordInvalidException() {
		return new EntityModelNotFoundException(PASSWORD_INVALID);
	}

	public static EntityModelDuplicatedException userAlreadyExistsException() {
		return new EntityModelDuplicatedException(USER_ALREADY_EXISTS);
	}

	public static EntityModelNotFoundException featureNotAsginedException() {
		return new EntityModelNotFoundException(FEATURE_NOT_ASIGNED);
	}
	
	public static EntityModelDuplicatedException featureAlreadyAsignedException() {
		return new EntityModelDuplicatedException(FEATURE_ALREADY_ASIGNED);
	}

	public static EntityModelNotFoundException capacityNotFoundException() {
		return new EntityModelNotFoundException(CAPACITY_NOT_FOUND);
	}
	
	public static EntityModelDuplicatedException capacityDuplicatedException() {
		return new EntityModelDuplicatedException(CAPACITY_DUPLICATED);
	}
	
	public static BusinessException userCapacityInsufficientException() {
		return new BusinessException(USER_CAPACITY_INSUFFICIENT);
	}

	public static BusinessException springDaysInsufficientException() {
		return new BusinessException(SPRING_DAYS_INSUFFICIENT);
	}
}
