package com.hhplanner.entities.model;

import java.util.Date;

public class Project {
	
	private int id;
	private String code;
	private String name;
    private Date startDate;
    private int springDays;

    public Project() {
		super();
	}
    
    public Project(int id, String code, String name, Date startDate, int springDays) {
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getSpringDays() {
		return springDays;
	}
	public void setSpringDays(int springDays) {
		this.springDays = springDays;
	}
	
}
