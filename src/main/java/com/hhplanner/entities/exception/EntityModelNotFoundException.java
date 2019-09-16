package com.hhplanner.entities.exception;

public class EntityModelNotFoundException extends BusinessException {

	private static final long serialVersionUID = 7672033519040613942L;

	public EntityModelNotFoundException() {
		this("Entity not found");
	}
	
	public EntityModelNotFoundException(String msg) {
		super(msg);
	}
	
}
