package com.hhplanner.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.SimpleIdCodeService;

@RestController
@RequestMapping("/api/simple-id-code")
public class SimpleIdCodeController extends SimpleController<Spring> {

	@Autowired	
	public SimpleIdCodeController(SimpleIdCodeService simpleService) {
		super(simpleService);
	}

}
