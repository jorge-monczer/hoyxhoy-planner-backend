package com.hhplanner.mockups;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.Spending;
import com.hhplanner.entities.service.SpendingService;

public class SpendingBuilder {
	private Spending spending;

	private AsignmentBuilder asignmentBuilder;
	private SpendingService service;

	public SpendingBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(SpendingService.class);
		this.asignmentBuilder = builderFactory.getAsignmentBuilder();
	}
	
	public SpendingBuilder buildSpending(int day,int spent) {
		this.spending = new Spending(this.asignmentBuilder.getAsignment(),day,spent);
		return this;
	}

	public SpendingBuilder buildSpendingsAndSave(Integer[] spendings) {
		for (int i = 0; i < spendings.length; i++) {
			if (spendings[i] != null) {
				this.buildSpending(i+1, spendings[i]).save();
			}
		}
		return this;
	}
	
	public SpendingBuilder buildSpendings(Integer[] spendings) {
		for (int i = 0; i < spendings.length; i++) {
			if (spendings[i] != null) {
				this.buildSpending(i+1, spendings[i]);
			}
		}
		return this;
	}

	public SpendingBuilder buildAsignment() {
		this.asignmentBuilder.buildAsignment(0).save();
		return this;
	}

	public SpendingBuilder buildCapacity(int capPerDay) {
		this.asignmentBuilder.buildCapacity(capPerDay);
		return this;
	}

	public SpendingBuilder buildS1() {
		this.asignmentBuilder.buildS1();
		return this;
	}

	public SpendingBuilder buildS2() {
		this.asignmentBuilder.buildS2();
		return this;
	}

	public SpendingBuilder buildS3() {
		this.asignmentBuilder.buildS3();
		return this;
	}

	public SpendingBuilder buildU1() {
		this.asignmentBuilder.buildU1();
		return this;
	}

	public SpendingBuilder buildU2() {
		this.asignmentBuilder.buildU2();
		return this;
	}

	public SpendingBuilder buildU3() {
		this.asignmentBuilder.buildU3();
		return this;
	}

	public SpendingBuilder buildF1() {
		this.asignmentBuilder.buildF1();
		return this;
	}

	public SpendingBuilder buildF2() {
		this.asignmentBuilder.buildF2();
		return this;
	}

	public SpendingBuilder buildF3() {
		this.asignmentBuilder.buildF3();
		return this;
	}

	public SpendingBuilder buildP1() {
		this.asignmentBuilder.buildP1();
		return this;
	}

	public SpendingBuilder buildP2() {
		this.asignmentBuilder.buildP2();
		return this;
	}

	public Spending getSpending() {
		this.asignmentBuilder.getAsignment();
		if (this.spending == null) {
			throw new BusinessException("Must build the spending");
		}
		return this.spending;
	}
	
	public SpendingBuilder save() {
		this.service.saveAndFlush(this.getSpending(),this.asignmentBuilder.getAsignment().getId(),this.getSpending().getId().getNumDay());
		return this;
	}
	
	public int getAsignmentId() {
		return this.asignmentBuilder.getAsignment().getId();
	}
	
	public void deleteAll() {
		this.service.deleteAll();
		this.asignmentBuilder.deleteAll();
	}

}
