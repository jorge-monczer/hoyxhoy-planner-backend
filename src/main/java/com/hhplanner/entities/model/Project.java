package com.hhplanner.entities.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hhplanner.entities.converter.LocalDateDeserializer;
import com.hhplanner.entities.converter.LocalDateSerializer;

@Entity
public class Project {
	
	@Id
	@GeneratedValue
	private int id;

    @Column(unique=true, name = "code", length = 8)
	private String code;
    @Column(unique=true, name = "name", length = 60)
    private String name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)    
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "spring_days")
    private int springDays;

    public Project() {
		super();
	}
    
    public Project(int id, String code, String name, LocalDate startDate, int springDays) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.startDate = startDate;
		this.springDays = springDays;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public int getSpringDays() {
		return springDays;
	}
	public void setSpringDays(int springDays) {
		this.springDays = springDays;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("<Project> {")
				.append("'id':").append(this.id).append(",")
				.append("'code':").append("'").append(this.code).append("'").append(",")
				.append("'name':").append("'").append(this.name).append("'").append(",")
				.append("'startDate':").append("'").append(this.startDate != null ? this.startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "null").append("'").append(",")
				.append("'springDays':").append(this.springDays).append("}").toString();
	}
	
}
