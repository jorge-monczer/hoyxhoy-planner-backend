package com.hhplanner.entities.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hhplanner.entities.converter.LocalDateDeserializer;
import com.hhplanner.entities.converter.LocalDateSerializer;

@Entity
@Table(name = "holiday")
public class Holiday {

	@Id
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "date", columnDefinition = "DATE")
	private LocalDate date;

    @Column(name = "description", length = 60)
    private String description;

    public Holiday() {
    }
    
    public Holiday(LocalDate date, String description) {
    	this.date = date;
    	this.description = description;
    }
    
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
