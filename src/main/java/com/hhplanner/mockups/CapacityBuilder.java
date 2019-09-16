package com.hhplanner.mockups;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.service.CapacityService;

@Component
@Scope("prototype")
public class CapacityBuilder {
	private Capacity capacity;

	private SpringBuilder springBuilder;
	private UserBuilder userBuilder;
	private CapacityService service;

	public CapacityBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(CapacityService.class);
		this.springBuilder = builderFactory.getSpringBuilder();
		this.userBuilder = builderFactory.getUserBuilder();
	}
	
	public CapacityBuilder buildCapacity(int id,int capPerDay) {
		this.capacity = new Capacity(id,this.userBuilder.getUser(),capPerDay);
		return this;
	}

	public CapacityBuilder buildS1() {
		this.springBuilder.buildS1(0).save();
		return this;
	}

	public CapacityBuilder buildS2() {
		this.springBuilder.buildS2(0).save();
		return this;
	}
	
	public CapacityBuilder buildS3() {
		this.springBuilder.buildS3(0).save();
		return this;
	}

	public CapacityBuilder buildU1() {
		this.userBuilder.buildU1().save();
		return this;
	}

	public CapacityBuilder buildU2() {
		this.userBuilder.buildU2().save();
		return this;
	}
	
	public CapacityBuilder buildU3() {
		this.userBuilder.buildU3().save();
		return this;
	}
	
	public CapacityBuilder buildP1() {
		this.springBuilder.buildP1();
		return this;
	}

	public CapacityBuilder buildP2() {
		this.springBuilder.buildP2();
		return this;
	}
	
	public Capacity getCapacity() {
		this.springBuilder.getSpring();
		if (this.capacity == null) {
			throw new BusinessException("Must build the capacity");
		}
		return this.capacity;
	}
	
	public CapacityBuilder save() {
		this.service.saveAndFlush(getCapacity(),this.springBuilder.getSpring().getId());
		return this;
	}
	
	public int getSpringId() {
		return this.springBuilder.getSpring().getId();
	}
	
	public void deleteAll() {
		this.service.deleteAll();
		this.userBuilder.deleteAll();
		this.springBuilder.deleteAll();
	}

}
