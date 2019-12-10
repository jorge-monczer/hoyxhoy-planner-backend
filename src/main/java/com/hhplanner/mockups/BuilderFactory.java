package com.hhplanner.mockups;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuilderFactory {

	@Autowired
	private BeanFactory beanFactory;
	
	private ProjectBuilder projectBuilder;
	private SpringBuilder springBuilder;
	private UserBuilder userBuilder;
	private CapacityBuilder capacityBuilder;
	private FeatureBuilder featureBuilder;
	private AsignmentBuilder asignmentBuilder;
	private SpendingBuilder spendingBuilder;
	private HolidayBuilder holidayBuilder;
	
	public void init() {
		this.projectBuilder = null;
		this.springBuilder = null;
		this.userBuilder = null;
		this.capacityBuilder = null;
		this.featureBuilder = null;
		this.asignmentBuilder = null;
		this.spendingBuilder = null;
		this.holidayBuilder = null;
	}
	
	public BeanFactory getBeanFactory() {
		return this.beanFactory;
	}

	public ProjectBuilder getProjectBuilder() {
		if (this.projectBuilder == null) {
			this.projectBuilder = new ProjectBuilder(this);
		}
		return projectBuilder;
	}

	public SpringBuilder getSpringBuilder() {
		if (this.springBuilder == null) {
			this.springBuilder = new SpringBuilder(this);
		}
		return springBuilder;
	}

	public UserBuilder getUserBuilder() {
		if (this.userBuilder == null) {
			this.userBuilder = new UserBuilder(this);
		}
		return userBuilder;
	}

	public CapacityBuilder getCapacityBuilder() {
		if (this.capacityBuilder == null) {
			this.capacityBuilder = new CapacityBuilder(this);
		}
		return capacityBuilder;
	}

	public FeatureBuilder getFeatureBuilder() {
		if (this.featureBuilder == null) {
			this.featureBuilder = new FeatureBuilder(this);
		}
		return featureBuilder;
	}

	public AsignmentBuilder getAsignmentBuilder() {
		if (this.asignmentBuilder == null) {
			this.asignmentBuilder = new AsignmentBuilder(this);
		}
		return asignmentBuilder;
	}
	
	public SpendingBuilder getSpendingBuilder() {
		if (this.spendingBuilder == null) {
			this.spendingBuilder = new SpendingBuilder(this);
		}
		return this.spendingBuilder;
	}
	
	public HolidayBuilder getHolidayBuilder() {
		if (this.holidayBuilder == null) {
			this.holidayBuilder = new HolidayBuilder(this);
		}
		return holidayBuilder;
	}

	
}
