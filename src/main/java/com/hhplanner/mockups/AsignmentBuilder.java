package com.hhplanner.mockups;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.service.AsignmentService;

public class AsignmentBuilder {
	private Asignment asignment;

	protected SpringBuilder springBuilder;
	private UserBuilder userBuilder;
	private FeatureBuilder featureBuilder;
	private CapacityBuilder capacityBuilder;
	private AsignmentService service;

	public AsignmentBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(AsignmentService.class);
		this.springBuilder = builderFactory.getSpringBuilder();
		this.userBuilder = builderFactory.getUserBuilder();
		this.featureBuilder = builderFactory.getFeatureBuilder();
		this.capacityBuilder = builderFactory.getCapacityBuilder();
	}
	
	
	public AsignmentBuilder buildAsignment(int id) {
		this.asignment = new Asignment(id,featureBuilder.getFeature(),userBuilder.getUser());
		return this;
	}

	public AsignmentBuilder buildCapacity(float capPerDay) {
		this.capacityBuilder.buildCapacity(0, capPerDay).save();
		return this;
	}
	
	public AsignmentBuilder buildS1() {
		this.springBuilder.buildS1(0).save();
		return this;
	}

	public AsignmentBuilder buildS2() {
		this.springBuilder.buildS2(0).save();
		return this;
	}
	
	public AsignmentBuilder buildS3() {
		this.springBuilder.buildS3(0).save();
		return this;
	}

	public AsignmentBuilder buildU1() {
		this.userBuilder.buildU1().save();
		return this;
	}

	public AsignmentBuilder buildU2() {
		this.userBuilder.buildU2().save();
		return this;
	}
	
	public AsignmentBuilder buildU3() {
		this.userBuilder.buildU3().save();
		return this;
	}

	public AsignmentBuilder buildF1() {
		this.featureBuilder.buildF1(0).save();
		return this;
	}
	
	public AsignmentBuilder buildF2() {
		this.featureBuilder.buildF2(0).save();
		return this;
	}

	public AsignmentBuilder buildF3() {
		this.featureBuilder.buildF3(0).save();
		return this;
	}
	
	public AsignmentBuilder buildP1() {
		this.featureBuilder.buildP1();
		return this;
	}

	public AsignmentBuilder buildP2() {
		this.featureBuilder.buildP2();
		return this;
	}
	
	public Asignment getAsignment() {
		this.springBuilder.getSpring();
		if (this.asignment == null) {
			throw new BusinessException("Must build the asignment");
		}
		return this.asignment;
	}
	
	public AsignmentBuilder save() {
		this.service.saveAndFlush(getAsignment(),this.springBuilder.getSpring().getId());
		return this;
	}
	
	public int getSpringId() {
		return this.springBuilder.getSpring().getId();
	}

	public int getProjectId() {
		return this.springBuilder.getProjectId();
	}
	
	public Feature getFeature() {
		return this.featureBuilder.getFeature();
	}
	
	public void deleteAll() {
		this.service.deleteAll();
		this.capacityBuilder.deleteAll();
		this.userBuilder.deleteAll();
		this.springBuilder.deleteAll();
	}

}
