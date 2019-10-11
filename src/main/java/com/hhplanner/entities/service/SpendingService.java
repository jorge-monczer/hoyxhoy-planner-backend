package com.hhplanner.entities.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Spending;
import com.hhplanner.entities.model.SpendingId;
import com.hhplanner.entities.repo.SpendingRepository;

@Service
@Transactional
public class SpendingService {

	private SpendingRepository spendingRepository;
	private AsignmentService asignmentService;
	
	public SpendingService(SpendingRepository spendingRepository, AsignmentService capacityService ) {
		this.spendingRepository = spendingRepository;
		this.asignmentService = capacityService;
	}

	public Spending getSpendingById(int asignmentId,int numDay) {
		Asignment asignment = this.asignmentService.getAsignmentById(asignmentId);
		Optional<Spending> spending = this.spendingRepository.findById(new SpendingId(asignment,numDay));
		if (!spending.isPresent()) {
			throw BusinessExceptionFactory.spendingNotFoundException();
		}
		return spending.get();
	}

	public Iterable<Spending> getSpendingsByAsignmentId(int asignmentId) {
		Iterable<Spending> spendings = this.spendingRepository.findByIdAsignmentIdOrderByIdNumDayAsc(asignmentId);
		return spendings;
	}

	@Transactional(rollbackOn = BusinessException.class)
	public Spending save(Spending spending, int asignmentId, int numDay) {
		Asignment asignment = this.asignmentService.getAsignmentById(asignmentId);
		SpendingId id = new SpendingId(asignment, numDay);
		if (this.spendingRepository.existsById(id)) {
			throw BusinessExceptionFactory.spendingDuplicatedException();
		}
		spending.setId(id);
		Spending save = this.spendingRepository.save(spending);
		return save;
	}
	
	
	public Spending saveAndFlush(Spending spending, int asignmentId, int numDay) {
		Spending save = save(spending,asignmentId, numDay);
		this.spendingRepository.flush();
		return save;
	}
	
	@Transactional(rollbackOn = BusinessException.class)
	public Spending update(Spending spending, int asignmentId, int numDay) {
		Asignment asignment = this.asignmentService.getAsignmentById(asignmentId);
		SpendingId id = new SpendingId(asignment, numDay);
		if (!this.spendingRepository.existsById(id)) {
			throw BusinessExceptionFactory.spendingNotFoundException();
		}
		spending.setId(id);
		Spending save = this.spendingRepository.save(spending);
		return save;
	}

	@Transactional(rollbackOn = BusinessException.class)
	public void delete(int asignmentId, int numDay) {
		Asignment asignment = this.asignmentService.getAsignmentById(asignmentId);
		this.spendingRepository.deleteById(new SpendingId(asignment,numDay));
	}

	@Transactional(rollbackOn = BusinessException.class)
	public void deleteByAsignmentId(int asignmentId) {
		this.spendingRepository.deleteByIdAsignmentId(asignmentId);
	}

	@Transactional(rollbackOn = BusinessException.class)
	public void deleteAll() {
		this.spendingRepository.deleteAll();
	}
	
}
