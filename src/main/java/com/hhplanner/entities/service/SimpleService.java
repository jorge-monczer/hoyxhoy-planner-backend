package com.hhplanner.entities.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.SimpleIdCode;
import com.hhplanner.entities.repo.SimpleRepository;

public class SimpleService<T extends SimpleIdCode> {

	protected SimpleRepository<T> simpleRepository;
	
	public SimpleService(SimpleRepository<T> simpleRepository) {
		this.simpleRepository = simpleRepository;
	}

	public T getItById(int id) {
		Optional<T> project = this.simpleRepository.findById(id);
		if (!project.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return project.get();
	}

	public T getItByCode(String code) {
		Optional<T> project = this.simpleRepository.findByCode(code);
		if (!project.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return project.get();
	}

	public Iterable<T> getThem() {
		return this.simpleRepository.findAll();
	}

	public T save(T entity) {
		try {
			return this.simpleRepository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		}
	}

	public T update(int id,T entity) {
		if (!this.simpleRepository.existsById(id)) {
			throw new EntityModelNotFoundException();
		}
		try {
			entity.setId(id);
			return this.simpleRepository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} 
	}

	public void delete(int id) {
		this.simpleRepository.deleteById(id);
	}

	public void deleteAll() {
		this.simpleRepository.deleteAll();
	}

	
}
