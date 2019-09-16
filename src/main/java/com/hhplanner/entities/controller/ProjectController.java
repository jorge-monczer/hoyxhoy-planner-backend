package com.hhplanner.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class ProjectController extends BasicController {

	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/projects")
	public ResponseEntity<Iterable<Project>> getAllProjects() {
		Iterable<Project> projects = this.projectService.getProjects();
		if (CollectionUtils.isEmpty(projects)) {
			return new ResponseEntity<>(projects, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(projects, HttpStatus.OK);		
	}

	@GetMapping("/projects/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.projectService.getProjectById(id), HttpStatus.OK);
	}

	@GetMapping("/projects/code/{code}")
	public ResponseEntity<Project> getProjectByCode(@PathVariable("code") String code) {
		return new ResponseEntity<>(this.projectService.getProjectByCode(code), HttpStatus.OK);
	}
	
	@PostMapping(value = "/projects")
	public ResponseEntity<Project> postProject(@RequestBody Project project) {
		try {
			return new ResponseEntity<>(this.projectService.save(project), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.projectDuplicatedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}

	@PutMapping("/projects/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable("id") int id, @RequestBody Project project) {
		try {
			return new ResponseEntity<>(this.projectService.update(id, project),HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.projectDuplicatedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}
	
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable("id") int id) {
		this.projectService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/projects")
	public ResponseEntity<Void> deleteProjects() {
		this.projectService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
