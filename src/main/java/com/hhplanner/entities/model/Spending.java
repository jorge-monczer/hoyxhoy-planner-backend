package com.hhplanner.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Spending {

	@Id
	private SpendingId id;
	
	@Column(name = "spent")
    private float spent;

    public Spending() {
		super();
	}
    
    public Spending(Asignment asignment, int numDay, float spent) {
		super();
		this.id = new SpendingId(asignment,numDay);
		this.spent = spent;
	}
    
	public SpendingId getId() {
		return id;
	}

	public void setId(SpendingId id) {
		this.id = id;
	}

	public float getSpent() {
		return spent;
	}

	public void setSpent(float spent) {
		this.spent = spent;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("<Spending> {")
				.append("'asingment':").append(this.id.getAsignment()).append(",")
				.append("'numDay':").append(this.id.getNumDay()).append(",")
				.append("'spent':").append(this.spent).append("}")
				.toString();
	}
	
}
