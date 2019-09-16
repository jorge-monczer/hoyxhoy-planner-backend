package com.hhplanner.mockups;

import java.time.LocalDate;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.service.ProjectService;

public class ProjectBuilder {
	private Project project;

  	private ProjectService service;
	
	public ProjectBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(ProjectService.class);
	}
	
	public ProjectBuilder buildP1(int id) {
		this.project = new Project(id, "P1", "Project 1", LocalDate.of(2019, 10, 1), 10);
		return this;
	}

	public ProjectBuilder buildP2(int id) {
		this.project = new Project(id, "P2", "Project 2", LocalDate.of(2019, 10, 15), 15);
		return this;
	}
	
	public ProjectBuilder buildP3(int id) {
		this.project = new Project(id, "P3", "Project 3", LocalDate.of(2019, 11, 1), 20);
		return this;
	}
 
	public Project getProject() {
		if (this.project == null) {
			throw new BusinessException("Must build the project");
		}
		return this.project;
	}

	public ProjectBuilder save() {
		this.service.saveAndFlush(this.getProject());
		return this;
	}
	
	public void deleteAll() {
		this.service.deleteAll();
	}
	
}
