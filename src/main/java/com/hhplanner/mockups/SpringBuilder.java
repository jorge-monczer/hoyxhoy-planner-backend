package com.hhplanner.mockups;

import java.time.LocalDate;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.SpringService;

public class SpringBuilder {
	private Spring spring;

	protected ProjectBuilder projectBuilder;
	private SpringService service;

	public SpringBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(SpringService.class);
		this.projectBuilder = builderFactory.getProjectBuilder();
	}
	
	public SpringBuilder buildS1(int id) {
		this.spring = new Spring(id, "S1", "Spring 1", LocalDate.of(2019, 10, 1),LocalDate.of(2019, 10, 10), 10);
		return this;
	}
	
	public SpringBuilder buildS2(int id) {
		this.spring = new Spring(id, "S2", "Spring 2", LocalDate.of(2019, 10, 2),LocalDate.of(2019, 10, 17), 15);
		return this;
	}

	public SpringBuilder buildS3(int id) {
		this.spring = new Spring(id, "S3", "Spring 3", LocalDate.of(2019, 10, 3),LocalDate.of(2019, 10, 23), 20);
		return this;
	}
	
	public SpringBuilder buildP1() {
		this.projectBuilder.buildP1(0).save();
		return this;
	}

	public SpringBuilder buildP2() {
		this.projectBuilder.buildP2(0).save();
		return this;
	}
	
	public Spring getSpring() {
		this.projectBuilder.getProject();
		if (this.spring == null) {
			throw new BusinessException("Must build the spring");
		}
		return this.spring;
	}
	
	public SpringBuilder save() {
		this.service.saveAndFlush(getSpring(),this.projectBuilder.getProject().getId());
		return this;
	}
	
	public void setProjectBuilder(ProjectBuilder projectBuilder) {
		this.projectBuilder = projectBuilder;
	}
	
	public int getProjectId() {
		return this.projectBuilder.getProject().getId();
	}
	
	public void deleteAll() {
		this.service.deleteAll();
		this.projectBuilder.deleteAll();
	}

}
