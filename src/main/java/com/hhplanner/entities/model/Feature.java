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
          @UniqueConstraint(columnNames={"project_id", "title"})  })
public class Feature implements SimpleIdCode {
	
	@Id
	@GeneratedValue
	private int id;

    @Column(unique=true, name = "code", length = 8)
	private String code;
    @Column(unique=true, name = "title", length = 120)
    private String title;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "committed_date", columnDefinition = "DATE")
    private LocalDate committedDate;
    @Column(name = "estimated_hours")
    private float estimatedHours;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;

    public Feature() {
		super();
	}
    
    public Feature(int id, String code, String title, LocalDate committedDate, float estimatedHours) {
		super();
		this.id = id;
		this.code = code;
		this.title = title;
		this.committedDate = committedDate;
		this.estimatedHours = estimatedHours;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getCommittedDate() {
		return committedDate;
	}
	public void setCommittedDate(LocalDate committedDate) {
		this.committedDate = committedDate;
	}
	public float getEstimatedHours() {
		return estimatedHours;
	}
	public void setEstimatedHours(float estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("<Feature> {")
				.append("'id':").append(this.id).append(",")
				.append("'code':").append("'").append(this.code).append("'").append(",")
				.append("'title':").append("'").append(this.title).append("'").append(",")
				.append("'committedDate':").append("'").append(this.committedDate != null ? this.committedDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "null").append("'").append(",")
				.append("'estimatedHours':").append(this.estimatedHours).append("}").toString();
	}
	
}
