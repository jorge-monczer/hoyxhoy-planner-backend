package com.hhplanner.entities.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.repo.ProjectRepository;

@Service
@Transactional
public class ProjectService {

	private ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public Project getProjectById(int id) {
		Optional<Project> project = this.projectRepository.findById(id);
		if (!project.isPresent()) {
			throw BusinessExceptionFactory.projectNotFoundException();
		}
		return project.get();
	}

	public Project getProjectByCode(String code) {
		Optional<Project> project = this.projectRepository.findByCode(code);
		if (!project.isPresent()) {
			throw BusinessExceptionFactory.projectNotFoundException();
		}
		return project.get();
	}

	public Iterable<Project> getProjects() {
		return this.projectRepository.findAll();
	}

	public Project save(Project project) {
		return this.projectRepository.save(project);
	}

	public Project saveAndFlush(Project project) {
		Project save = save(project);
		this.projectRepository.flush();
		return save;
	}
	
	public Project update(int id,Project project) {
		if (!this.projectRepository.existsById(id)) {
			throw BusinessExceptionFactory.projectNotFoundException();
		}
		project.setId(id);
		return this.projectRepository.save(project);
	}

	public void delete(int id) {
		this.projectRepository.deleteById(id);
	}

	public void deleteAll() {
		this.projectRepository.deleteAll();
	}

}
