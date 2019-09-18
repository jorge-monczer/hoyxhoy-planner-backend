package com.hhplanner.entities.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Asignment {
	
	@Id
	@GeneratedValue
	private int id;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(unique=true, name = "feature_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Feature feature;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "spring_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
	private Spring spring;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "username", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User1 user;

	@Formula(value = "(select c.available_hours * s.spring_days - f.estimated_hours from capacity c, spring s, feature f where f.id = feature_id and s.id = spring_id and c.spring_id = spring_id and c.username = username)")
	private double remaining;
	
    public Asignment() {
		super();
	}
    
    public Asignment(int id, Feature feature, User1 user) {
		super();
		this.id = id;
		this.feature = feature;
		this.user = user;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public Spring getSpring() {
		return spring;
	}
	public void setSpring(Spring spring) {
		this.spring = spring;
	}
	public User1 getUser() {
		return user;
	}
	public void setUser(User1 user) {
		this.user = user;
	}
	public double getRemaining() {
		return remaining;
	}
	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("<Asignment> {")
				.append("'id':").append(this.id).append(",")
				.append("'feature':").append("'").append(this.feature.getCode()).append("'").append(",")
				.append("'user':").append("'").append(this.user.getUsername()).append("'").append("}").toString();
	}
	
}
