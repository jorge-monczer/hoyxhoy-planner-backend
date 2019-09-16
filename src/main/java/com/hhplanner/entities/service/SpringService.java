package com.hhplanner.entities.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.repo.AsignmentRepository;
import com.hhplanner.entities.repo.AsignmentRepository.Q_CapacitySumary;
import com.hhplanner.entities.repo.SpringRepository;

@Service
@Transactional
public class SpringService {

	private SpringRepository springRepository;
	private ProjectService projectService;
	private AsignmentRepository asignmentRepository;
	
	public SpringService(SpringRepository springRepository, ProjectService projectService, AsignmentRepository asignmentRepository ) {
		this.springRepository = springRepository;
		this.projectService = projectService;
		this.asignmentRepository = asignmentRepository;
	}

	public Spring getSpringById(int id) {
		Optional<Spring> spring = this.springRepository.findById(id);
		if (!spring.isPresent()) {
			throw BusinessExceptionFactory.springNotFoundException();
		}
		return spring.get();
	}

	public Spring getSpringByCodeAndProjectId( String code, int projectId) {
		Optional<Spring> spring = this.springRepository.findByCodeAndProjectId(code, projectId);
		if (!spring.isPresent()) {
			throw BusinessExceptionFactory.springNotFoundException();
		}
		return spring.get();
	}

	public Iterable<Spring> getSpringsByProjectId(int projectId) {
		return this.springRepository.findByProjectIdOrderByStartDate(projectId);
	}

	public Spring save(Spring spring, int projectId) {
		Project project = this.projectService.getProjectById(projectId);
		spring.setProject(project);
		Spring save = this.springRepository.save(spring);
		return save;
	}

	public Spring saveAndFlush(Spring spring, int projectId) {
		Spring save = save(spring,projectId);
		this.springRepository.flush();
		return save;
	}
	
	@Transactional(rollbackOn = BusinessException.class)
	public Spring update(int id,Spring spring, int projectId) {
		if (!this.springRepository.existsById(id)) {
			throw BusinessExceptionFactory.springNotFoundException();
		}
		Project project = this.projectService.getProjectById(projectId);
		spring.setId(id);
		spring.setId(id);
		spring.setProject(project);
		Spring save = this.springRepository.save(spring);
		if (!this.validateEnoughSpringDays(spring)) {
			throw BusinessExceptionFactory.springDaysInsufficientException();			
		};
		return save;
	}

	public boolean validateEnoughSpringDays(Spring spring) {
		List<Q_CapacitySumary> listCapacitySumary = this.asignmentRepository.sumHoursAsignedForUserBySpringId(spring.getId());
		listCapacitySumary.forEach(s -> {  System.out.println(s);});
		listCapacitySumary.forEach(System.out::println);
		OptionalDouble maxDaysAsigned = listCapacitySumary.stream().mapToDouble(s -> (double) s.getEstimatedHours() / s.getAvailableHours() ).max();
		return 	!maxDaysAsigned.isPresent() || spring.getSpringDays() >= maxDaysAsigned.getAsDouble();
	}
	
	public void delete(int id) {
		this.springRepository.deleteById(id);
	}

	public void deleteAllByProjectId(int projectId) {
		this.springRepository.deleteByProjectId(projectId);
	}

	public void deleteAll() {
		this.springRepository.deleteAll();
	}
	
}
