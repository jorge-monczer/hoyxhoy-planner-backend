package com.hhplanner.entities.exception;

public class SpringDaysInsufficientException extends BusinessException {

	private static final long serialVersionUID = -1636892545030392747L;
	
	public SpringDaysInsufficientException() {
		super();
	}

	public SpringDaysInsufficientException(String message) {
		super(message);
	}
}
