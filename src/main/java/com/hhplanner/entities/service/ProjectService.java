package com.hhplanner.entities.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.repo.ProjectRepository;

@Service
public class ProjectService {

	private ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public Project getProjectById(int id) {
		Optional<Project> project = this.projectRepository.findById(id);
		if (!project.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return project.get();
	}

	public Project getProjectByCode(String code) {
		Optional<Project> project = this.projectRepository.findByCode(code);
		if (!project.isPresent()) {
			throw new EntityModelNotFoundException();
		}
		return project.get();
	}

	public Iterable<Project> getProjects() {
		return this.projectRepository.findAll();
	}

	public Project save(Project project) {
		try {
			return this.projectRepository.save(project);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		}
	}

	public Project saveAndFlush(Project project) {
		Project save = save(project);
		this.projectRepository.flush();
		return save;
	}
	
	public Project update(int id,Project project) {
		if (!this.projectRepository.existsById(id)) {
			throw new EntityModelNotFoundException();
		}
		try {
			project.setId(id);
			return this.projectRepository.save(project);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} 
	}

	public void delete(int id) {
		this.projectRepository.deleteById(id);
	}

	public void deleteAll() {
		this.projectRepository.deleteAll();
	}

}
