package com.hhplanner.entities.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;

public class BasicController {

	@ExceptionHandler(EntityModelNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody
	   Map<String,Object> handleIndexNotFoundException(EntityModelNotFoundException bre, HttpServletRequest request, HttpServletResponse resp) {
	     HashMap<String, Object> result = new HashMap<>();
	     result.put("error", true);
	     result.put("error_message", bre.getMessage());
	     return result;
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody
	   Map<String,Object> handleIndexNotFoundException(BusinessException bre, HttpServletRequest request, HttpServletResponse resp) {
	     HashMap<String, Object> result = new HashMap<>();
	     result.put("error", true);
	     result.put("error_message", bre.getMessage());
	     return result;
	}
	
}
