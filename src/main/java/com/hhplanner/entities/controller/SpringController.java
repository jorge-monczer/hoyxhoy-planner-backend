package com.hhplanner.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.SpringService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class SpringController {

	private final SpringService springService;
	
	@Autowired
	public SpringController(SpringService springService) {
		this.springService = springService;
	}
	
	@GetMapping("/projects/{pid}/springs")
	public ResponseEntity<Iterable<Spring>> getAllSpringsByProjectID(@PathVariable("pid") int pid) {
		Iterable<Spring> springs = this.springService.getSpringsByProjectId(pid);
		if (CollectionUtils.isEmpty(springs)) {
			return new ResponseEntity<>(springs, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(springs, HttpStatus.OK);		
	}

	@GetMapping("/projects/{pid}/springs/{id}")
	public ResponseEntity<Spring> getSpringById(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.springService.getSpringById(id), HttpStatus.OK);
	}

	@GetMapping("/projects/{pid}/springs/code/{code}")
	public ResponseEntity<Spring> getSpringByCode(@PathVariable("pid") int pid,@PathVariable("code") String code) {
		return new ResponseEntity<>(this.springService.getSpringByCodeAndProjectId(code, pid), HttpStatus.OK);
	}
	
	@PostMapping(value = "/projects/{pid}/springs")
	public ResponseEntity<Spring> postSpring(@PathVariable("pid") int pid,@RequestBody Spring spring) {
		return new ResponseEntity<>(this.springService.save(spring, pid), HttpStatus.CREATED);
	}

	@PutMapping("/projects/{pid}/springs/{id}")
	public ResponseEntity<Spring> updateSpring(@PathVariable("pid") int pid,@PathVariable("id") int id, @RequestBody Spring spring) {
		return new ResponseEntity<>(this.springService.update(id, spring, pid),HttpStatus.OK);
	}
	
	@DeleteMapping("/projects/{pid}/springs/{id}")
	public ResponseEntity<Void> deletSpring(@PathVariable("pid") int pid, @PathVariable("id") int id) {
		this.springService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/projects/{pid}/springs")
	public ResponseEntity<Void> deleteSprings(@PathVariable("pid") int pid) {
		this.springService.deleteAllByProjectId(pid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
