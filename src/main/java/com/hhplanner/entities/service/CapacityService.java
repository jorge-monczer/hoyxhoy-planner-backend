package com.hhplanner.entities.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.repo.CapacityRepository;

@Service
public class CapacityService {

	private CapacityRepository capacityRepository;
	private SpringService springService;
	
	public CapacityService(CapacityRepository capacityRepository, SpringService springService ) {
		this.capacityRepository = capacityRepository;
		this.springService = springService;
	}

	public Capacity getCapacityById(int id) {
		Optional<Capacity> capacity = this.capacityRepository.findById(id);
		if (!capacity.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return capacity.get();
	}

	public Capacity getCapacityByUserAndSpringId( User1 user, int springId) {
		Optional<Capacity> capacity = this.capacityRepository.findByUserAndSpringId(user, springId);
		if (!capacity.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return capacity.get();
	}

	public Iterable<Capacity> getCapacitysBySpringId(int springId) {
		return this.capacityRepository.findBySpringId(springId);
	}

	public Capacity save(Capacity capacity, int springId) {
		Spring spring = this.springService.getSpringById(springId);
		try {
			capacity.setSpring(spring);
			Capacity save = this.capacityRepository.save(capacity);
//			this.springRepository.flush();
			return save;
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} catch (Exception e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		}
	}

	public Capacity saveAndFlush(Capacity capacity,int springId) {
		Capacity save = save(capacity,springId);
		this.capacityRepository.flush();
		return save;
	}
	
	public Capacity update(int id,Capacity capacity, int springId) {
		if (!this.capacityRepository.existsById(id)) {
			throw new EntityModelNotFoundException();
		}
		Spring spring = this.springService.getSpringById(springId);
		try {
			capacity.setId(id);
			capacity.setSpring(spring);
			return this.capacityRepository.save(capacity);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} 
	}

	public void delete(int id) {
		this.capacityRepository.deleteById(id);
	}

	public void deleteAllBySpringId(int springId) {
		this.capacityRepository.deleteBySpringId(springId);
	}

	public void deleteAll() {
		this.capacityRepository.deleteAll();
	}
	
}
