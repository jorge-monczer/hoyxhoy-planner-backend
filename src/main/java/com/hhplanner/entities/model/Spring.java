package com.hhplanner.entities.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hhplanner.entities.converter.LocalDateDeserializer;
import com.hhplanner.entities.converter.LocalDateSerializer;

@Entity
@Table(uniqueConstraints= {
          @UniqueConstraint(columnNames = {"project_id", "code"}),
          @UniqueConstraint(columnNames={"project_id", "name"})  })
public class Spring implements SimpleIdCode {
	
	@Id
	@GeneratedValue
	private int id;

    @Column(unique=true, name = "code", length = 8)
	private String code;
    @Column(unique=true, name = "name", length = 60)
    private String name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
//    @Column(name = "start_date", columnDefinition = "DATE")
    @Transient
    private LocalDate startDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
//    @Column(name = "end_date", columnDefinition = "DATE")
    @Transient
    private LocalDate endDate;
    @Column(name = "spring_days")
    private int springDays;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;

    public Spring() {
		super();
	}
    
    public Spring(int id, String code, String name, int springDays) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
//		this.startDate = startDate;
//		this.endDate = endDate;
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
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getSpringDays() {
		return springDays;
	}
	public void setSpringDays(int springDays) {
		this.springDays = springDays;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("<Project> {")
				.append("'id':").append(this.id).append(",")
				.append("'code':").append("'").append(this.code).append("'").append(",")
				.append("'name':").append("'").append(this.name).append("'").append(",")
//				.append("'startDate':").append("'").append(this.startDate != null ? this.startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "null").append("'").append(",")
//				.append("'endDate':").append("'").append(this.endDate != null ? this.startDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "null").append("'").append(",")
				.append("'springDays':").append(this.springDays).append("}").toString();
	}
	
}
