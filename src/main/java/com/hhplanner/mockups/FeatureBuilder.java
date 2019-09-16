package com.hhplanner.mockups;

import java.time.LocalDate;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.service.FeatureService;

public class FeatureBuilder {
	private Feature feature;

	private ProjectBuilder projectBuilder;
	private FeatureService service;

	public FeatureBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(FeatureService.class);
		this.projectBuilder = builderFactory.getProjectBuilder();
	}
	
	public FeatureBuilder buildF1(int id) {
		this.feature = new Feature(id, "F1", "Feature 1", LocalDate.of(2019, 10, 1),40);
		return this;
	}
	
	public FeatureBuilder buildF2(int id) {
		this.feature = new Feature(id, "F2", "Feature 2", LocalDate.of(2019, 10, 2),52);
		return this;
	}

	public FeatureBuilder buildF3(int id) {
		this.feature = new Feature(id, "F3", "Feature 3", LocalDate.of(2019, 10, 3),24);
		return this;
	}
	
	public FeatureBuilder buildP1() {
		this.projectBuilder.buildP1(0).save();
		return this;
	}

	public FeatureBuilder buildP2() {
		this.projectBuilder.buildP2(0).save();
		return this;
	}
	
	public Feature getFeature() {
		this.projectBuilder.getProject();
		if (this.feature == null) {
			throw new BusinessException("Must build the feature");
		}
		return this.feature;
	}
	
	public FeatureBuilder save() {
		this.service.saveAndFlush(this.getFeature(),this.projectBuilder.getProject().getId());
		return this;
	}
	
	public int getProjectId() {
		return this.projectBuilder.getProject().getId();
	}
	
	public void deleteAll() {
		this.service.deleteAll();
		this.projectBuilder.deleteAll();
	}

}
