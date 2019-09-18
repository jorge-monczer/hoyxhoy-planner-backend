package com.hhplanner.entities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints= {
        @UniqueConstraint(columnNames = {"spring_id", "username"}) })
public class Capacity {
	
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "spring_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
	private Spring spring;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "username", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User1 user;

	
	@Column(name = "available_hours")
    private int availableHours;
	
	@Formula(value = "( select sum(f.estimated_hours) from asignment a, feature f where a.spring_id = spring_id and a.username = username and a.feature_id = f.id )")
	private Integer asignedOnSpring;
	
    public Capacity() {
		super();
	}
    
    public Capacity(int id, User1 user, int availableHours) {
		super();
		this.id = id;
		this.user = user;
		this.availableHours = availableHours;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getAvailableHours() {
		return availableHours;
	}
	public void setAvailableHours(int availableHours) {
		this.availableHours = availableHours;
	}

	public int getAvailableOnSpring() {
		if (this.getSpring() == null) {
			return 0;
		}
		return this.availableHours * this.getSpring().getSpringDays();
	}

	public int getRemainingOnSpring() {
		return this.getAvailableOnSpring() - (this.asignedOnSpring==null?0:this.asignedOnSpring);
	}

//	public void setRemainingOnSpring(int asignedOnSpring) {
//		this.asignedOnSpring = asignedOnSpring;
//	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("<Capacity> {")
				.append("'id':").append(this.id).append(",")
				.append("'user':").append("'").append(this.user.getUsername()).append("'").append(",")
				.append("'availableHours':").append(this.availableHours).append("}").toString();
	}
	
}
