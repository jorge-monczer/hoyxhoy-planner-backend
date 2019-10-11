package com.hhplanner.entities.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.repo.AsignmentRepository;

@Service
@Transactional
public class AsignmentService {

	private AsignmentRepository asignmentRepository;
	private SpringService springService;
	private CapacityService capacityService;
	
	public AsignmentService(AsignmentRepository asignmentRepository, SpringService springService, CapacityService capacityService ) {
		this.asignmentRepository = asignmentRepository;
		this.springService = springService;
		this.capacityService = capacityService;
	}

	public Asignment getAsignmentById(int id) {
		Optional<Asignment> optAsignment = this.asignmentRepository.findById(id);
		if (!optAsignment.isPresent()) {
			throw BusinessExceptionFactory.featureNotAsginedException();
		}
		Asignment asignment = optAsignment.get();
		asignment.buildIntFromSpendings();
		return asignment;
	}

	public Asignment getAsignmentByFeature( Feature feature) {
		Optional<Asignment> asignment = this.asignmentRepository.findByFeature(feature);
		if (!asignment.isPresent()) {
			throw BusinessExceptionFactory.featureNotAsginedException();
		}
		return asignment.get();
	}

	public Asignment getAsignmentByFeatureId( int fid) {
		Optional<Asignment> asignment = this.asignmentRepository.findByFeatureId(fid);
		if (!asignment.isPresent()) {
			throw BusinessExceptionFactory.featureNotAsginedException();
		}
		return asignment.get();
	}

	public boolean existsAsignmentByFeatureCode(String featureCode) {
		return this.asignmentRepository.existsByFeatureCode(featureCode);
	}
	
	public List<Asignment> getAsignmentsBySpringId(int springId) {
		List<Asignment> asignments = this.asignmentRepository.findBySpringIdOrderByUserAscFeatureAsc(springId);
		String username = "";
		double remaining = 0d;
		for (Asignment asignment : asignments) {
			if (asignment.getUser().getUsername().equals(username)) {
				asignment.setRemaining(remaining - asignment.getFeature().getEstimatedHours());
			}
			username = asignment.getUser().getUsername();
			remaining = asignment.getRemaining();
			asignment.buildIntFromSpendings();
		}
		return asignments;
	}

	@Transactional(rollbackOn = BusinessException.class)
	public Asignment save(Asignment asignment, int springId) {
		Spring spring = this.springService.getSpringById(springId);
		this.capacityService.getCapacityByUserAndSpringId(asignment.getUser(), spring.getId());
		asignment.setSpring(spring);
		Asignment save = this.asignmentRepository.save(asignment);
		if (!this.springService.validateEnoughSpringDays(spring)) {
			throw BusinessExceptionFactory.userCapacityInsufficientException();
		}
		return save;
	}
	
	public Asignment saveAndFlush(Asignment asignment,int springId) {
		Asignment save = save(asignment,springId);
		this.asignmentRepository.flush();
		return save;
	}
	
	@Transactional(rollbackOn = BusinessException.class)
	public Asignment update(int id,Asignment asignment, int springId) {
		if (!this.asignmentRepository.existsById(id)) {
			throw BusinessExceptionFactory.featureNotAsginedException();
		}
		Spring spring = this.springService.getSpringById(springId);
		this.capacityService.getCapacityByUserAndSpringId(asignment.getUser(), spring.getId());
		asignment.setId(id);
		asignment.setSpring(spring);
		asignment.buildSpendingsFromInt();
		Asignment save = this.asignmentRepository.save(asignment);
		if (!this.springService.validateEnoughSpringDays(spring)) {
			throw BusinessExceptionFactory.userCapacityInsufficientException();
		}
		save.buildIntFromSpendings();
		return save;
	}

	@Transactional(rollbackOn = BusinessException.class)
	public Asignment updateSpendings(Asignment asignment, Spring spring) {
		Asignment asignmentDB = this.getAsignmentById(asignment.getId());
		asignmentDB.setSpring(spring);
		asignmentDB.setSpendingsInt(asignment.getSpendingsInt());
		asignmentDB.buildSpendingsFromInt();
		Asignment save = this.asignmentRepository.save(asignmentDB);
		save.buildIntFromSpendings();
		return save;
	}
	
	@Transactional(rollbackOn = BusinessException.class)
	public List<Asignment> updateAllSpendings(List<Asignment> asignments, int springId) {
		Spring spring = this.springService.getSpringById(springId);
		return asignments.stream().map(as -> updateSpendings(as,spring)).collect(Collectors.toList());
	}

	
	public void delete(int id) {
		this.asignmentRepository.deleteById(id);
	}

	public void deleteAllBySpringId(int springId) {
		this.asignmentRepository.deleteBySpringId(springId);
	}

	public void deleteAll() {
		this.asignmentRepository.deleteAll();
	}
	
}
