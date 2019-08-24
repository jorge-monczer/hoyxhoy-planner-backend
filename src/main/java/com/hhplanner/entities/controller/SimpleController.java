package com.hhplanner.entities.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hhplanner.entities.model.SimpleIdCode;
import com.hhplanner.entities.service.SimpleService;
import com.hhplanner.utils.CollectionUtils;

public class SimpleController<T extends SimpleIdCode> {

	protected SimpleService<T> simpleService;
	
	public SimpleController(SimpleService<T> simpleService) {
		this.simpleService = simpleService;
	}
	
	@GetMapping("")
	public ResponseEntity<Iterable<T>> getAll() {
		Iterable<T> projects = this.simpleService.getThem();
		if (CollectionUtils.isEmpty(projects)) {
			return new ResponseEntity<>(projects, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(projects, HttpStatus.OK);		
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> getProjectById(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.simpleService.getItById(id), HttpStatus.OK);
	}

	@GetMapping("/code/{code}")
	public ResponseEntity<T> getProjectByCode(@PathVariable("code") String code) {
		return new ResponseEntity<>(this.simpleService.getItByCode(code), HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<T> postProject(@RequestBody T project) {
		return new ResponseEntity<>(this.simpleService.save(project), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<T> updateProject(@PathVariable("id") int id, @RequestBody T project) {
		return new ResponseEntity<>(this.simpleService.update(id, project),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable("id") int id) {
		this.simpleService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Void> deleteProjects() {
		this.simpleService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
