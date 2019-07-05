package com.hhplanner.entities.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhplanner.entities.model.Project;
import com.hhplanner.mockups.MockupsToTest;

@RestController
@RequestMapping("/api")
public class ProjectController {

	@GetMapping("/projects")
	public ResponseEntity<List<Project>> getAllProjects() {
		return new ResponseEntity<>(MockupsToTest.createProjectListToTest(), HttpStatus.OK);		
	}

	@GetMapping("/project/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
		return new ResponseEntity<>(MockupsToTest.createProjectTLMK(), HttpStatus.OK);
	}

	@GetMapping("/project/code/{code}")
	public ResponseEntity<Project> getProjectByCode(@PathVariable("code") String code) {
		return new ResponseEntity<>(MockupsToTest.createProjectTLMK(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/project")
	public ResponseEntity<Project> postProject(@RequestBody Project project) {
		Project _project = project;
		_project.setId(1);
		return new ResponseEntity<>(_project, HttpStatus.CREATED);
	}

	@DeleteMapping("/project/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable("id") long id) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/projects")
	public ResponseEntity<Void> deleteProjects() {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
