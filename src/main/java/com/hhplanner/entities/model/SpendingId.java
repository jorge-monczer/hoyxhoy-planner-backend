package com.hhplanner.entities.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class SpendingId implements Serializable {

	private static final long serialVersionUID = -4364752617991163697L;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asignment_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Asignment asignment;
	
	@Column(name = "num_day")
	private int numDay;

	public SpendingId() {
	}
	
	public SpendingId(Asignment asignment,int numDay) {
		this.asignment = asignment;
		this.numDay = numDay;
	}
    
	public Asignment getAsignment() {
		return asignment;
	}
	public void setAsignment(Asignment asignment) {
		this.asignment = asignment;
	}
	public int getNumDay() {
		return numDay;
	}
	public void setNumDay(int numDay) {
		this.numDay = numDay;
	}
	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpendingId)) return false;
        SpendingId that = (SpendingId) o;
        return getAsignment().getId() == that.getAsignment().getId() &&
               getNumDay()  == that.getNumDay();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getAsignment().getId(), getNumDay());
    }}
