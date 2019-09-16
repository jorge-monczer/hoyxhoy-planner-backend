package com.hhplanner.entities.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.repo.CapacityRepository;

@Service
@Transactional
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
			throw BusinessExceptionFactory.capacityNotFoundException();
		}
		return capacity.get();
	}

	public Capacity getCapacityByUserAndSpringId( User1 user, int springId) {
		Optional<Capacity> capacity = this.capacityRepository.findByUserAndSpringId(user, springId);
		if (!capacity.isPresent()) {
			throw BusinessExceptionFactory.capacityNotFoundException();
		}
		return capacity.get();
	}

	public Iterable<Capacity> getCapacitysBySpringId(int springId) {
		return this.capacityRepository.findBySpringId(springId);
	}

	public Capacity save(Capacity capacity, int springId) {
		Spring spring = this.springService.getSpringById(springId);
		capacity.setSpring(spring);
		Capacity save = this.capacityRepository.save(capacity);
		return save;
	}

	public Capacity saveAndFlush(Capacity capacity,int springId) {
		Capacity save = save(capacity,springId);
		this.capacityRepository.flush();
		return save;
	}
	
	@Transactional(rollbackOn = BusinessException.class)
	public Capacity update(int id,Capacity capacity, int springId) {
		if (!this.capacityRepository.existsById(id)) {
			throw BusinessExceptionFactory.capacityNotFoundException();
		}
		Spring spring = this.springService.getSpringById(springId);
		capacity.setId(id);
		capacity.setSpring(spring);
		Capacity save = this.capacityRepository.save(capacity);
		if (!this.springService.validateEnoughSpringDays(spring)) {
			throw BusinessExceptionFactory.userCapacityInsufficientException();
		}
		return save;
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
