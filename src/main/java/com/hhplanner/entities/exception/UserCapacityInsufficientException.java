package com.hhplanner.entities.exception;

public class UserCapacityInsufficientException extends BusinessException {

	private static final long serialVersionUID = -1636892545030392747L;
	
	public UserCapacityInsufficientException() {
		super();
	}

	public UserCapacityInsufficientException(String message) {
		super(message);
	}
}
