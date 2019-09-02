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

import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.service.FeatureService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class FeatureController {

	private final FeatureService featrueService;
	
	@Autowired
	public FeatureController(FeatureService featrueService) {
		this.featrueService = featrueService;
	}
	
	@GetMapping("/projects/{pid}/features")
	public ResponseEntity<Iterable<Feature>> getAllFeaturesByProjectID(@PathVariable("pid") int pid) {
		Iterable<Feature> features = this.featrueService.getFeaturesByProjectId(pid);
		if (CollectionUtils.isEmpty(features)) {
			return new ResponseEntity<>(features, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(features, HttpStatus.OK);		
	}

	@GetMapping("/projects/{pid}/features/{id}")
	public ResponseEntity<Feature> getFeatureById(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.featrueService.getFeatureById(id), HttpStatus.OK);
	}

	@GetMapping("/projects/{pid}/features/code/{code}")
	public ResponseEntity<Feature> getFeatureByCode(@PathVariable("pid") int pid,@PathVariable("code") String code) {
		return new ResponseEntity<>(this.featrueService.getFeatureByCodeAndProjectId(code, pid), HttpStatus.OK);
	}
	
	@PostMapping(value = "/projects/{pid}/features")
	public ResponseEntity<Feature> postFeature(@PathVariable("pid") int pid,@RequestBody Feature feature) {
		return new ResponseEntity<>(this.featrueService.save(feature, pid), HttpStatus.CREATED);
	}

	@PutMapping("/projects/{pid}/features/{id}")
	public ResponseEntity<Feature> updateFeature(@PathVariable("pid") int pid,@PathVariable("id") int id, @RequestBody Feature feature) {
		return new ResponseEntity<>(this.featrueService.update(id, feature, pid),HttpStatus.OK);
	}
	
	@DeleteMapping("/projects/{pid}/features/{id}")
	public ResponseEntity<Void> deleteFeature(@PathVariable("pid") int pid, @PathVariable("id") int id) {
		this.featrueService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/projects/{pid}/features")
	public ResponseEntity<Void> deleteFeatures(@PathVariable("pid") int pid) {
		this.featrueService.deleteAllByProjectId(pid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}