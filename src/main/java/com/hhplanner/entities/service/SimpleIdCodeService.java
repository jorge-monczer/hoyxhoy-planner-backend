package com.hhplanner.entities.service;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.repo.SimpleIdCodeRepository;

@Service
public class SimpleIdCodeService extends SimpleService<Spring> {

	public SimpleIdCodeService(SimpleIdCodeRepository simpleRepository) {
		super(simpleRepository);
	}

}
