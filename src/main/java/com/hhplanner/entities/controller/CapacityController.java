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

import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.service.CapacityService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class CapacityController {

	private final CapacityService capacityService;
	
	@Autowired
	public CapacityController(CapacityService capacityService) {
		this.capacityService = capacityService;
	}
	
	@GetMapping("/springs/{sid}/capacities")
	public ResponseEntity<Iterable<Capacity>> getAllCapacitysByProjectID(@PathVariable("sid") int sid) {
		Iterable<Capacity> capacities = this.capacityService.getCapacitysBySpringId(sid);
		if (CollectionUtils.isEmpty(capacities)) {
			return new ResponseEntity<>(capacities, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(capacities, HttpStatus.OK);		
	}

	@GetMapping("/springs/{sid}/capacities/{id}")
	public ResponseEntity<Capacity> getCapacityById(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.capacityService.getCapacityById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/springs/{sid}/capacities")
	public ResponseEntity<Capacity> postCapacity(@PathVariable("sid") int sid,@RequestBody Capacity capacity) {
		return new ResponseEntity<>(this.capacityService.save(capacity, sid), HttpStatus.CREATED);
	}

	@PutMapping("/springs/{sid}/capacities/{id}")
	public ResponseEntity<Capacity> updateCapacity(@PathVariable("sid") int sid,@PathVariable("id") int id, @RequestBody Capacity capacity) {
		return new ResponseEntity<>(this.capacityService.update(id, capacity, sid),HttpStatus.OK);
	}
	
	@DeleteMapping("/springs/{sid}/capacities/{id}")
	public ResponseEntity<Void> deleteCapacity(@PathVariable("sid") int sid, @PathVariable("id") int id) {
		this.capacityService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/springs/{sid}/capacities")
	public ResponseEntity<Void> deleteCapacities(@PathVariable("sid") int sid) {
		this.capacityService.deleteAllBySpringId(sid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
