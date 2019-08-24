package com.hhplanner.entities.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.repo.SpringRepository;

@Service
public class SpringService {

	private SpringRepository springRepository;
	private ProjectService projectService;
	
	public SpringService(SpringRepository springRepository, ProjectService projectService) {
		this.springRepository = springRepository;
		this.projectService = projectService;
	}

	public Spring getSpringById(int id) {
		Optional<Spring> spring = this.springRepository.findById(id);
		if (!spring.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return spring.get();
	}

	public Spring getSpringByCodeAndProjectId( String code, int projectId) {
		Optional<Spring> spring = this.springRepository.findByCodeAndProjectId(code, projectId);
		if (!spring.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return spring.get();
	}

	public Iterable<Spring> getSpringsByProjectId(int projectId) {
		return this.springRepository.findByProjectIdOrderByStartDate(projectId);
	}

	public Spring save(Spring spring, int projectId) {
		Project project = this.projectService.getProjectById(projectId);
		try {
			spring.setProject(project);
			Spring save = this.springRepository.save(spring);
//			this.springRepository.flush();
			return save;
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} catch (Exception e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		}
	}

	public Spring saveAndFlush(Spring spring, int projectId) {
		Spring save = save(spring,projectId);
		this.springRepository.flush();
		return save;
	}
	
	public Spring update(int id,Spring spring, int projectId) {
		if (!this.springRepository.existsById(id)) {
			throw new EntityModelNotFoundException();
		}
		Project project = this.projectService.getProjectById(projectId);
		try {
			spring.setId(id);
			spring.setProject(project);
			return this.springRepository.save(spring);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} 
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
