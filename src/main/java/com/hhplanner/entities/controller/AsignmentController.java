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
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.service.AsignmentService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class AsignmentController extends BasicController {

	private final AsignmentService asignmentService;
	
	@Autowired
	public AsignmentController(AsignmentService asignmentService) {
		this.asignmentService = asignmentService;
	}
	
	@GetMapping("/springs/{sid}/asignments")
	public ResponseEntity<Iterable<Asignment>> getAllAsignmentsByProjectID(@PathVariable("sid") int sid) {
		Iterable<Asignment> asignments = this.asignmentService.getAsignmentsBySpringId(sid);
		if (CollectionUtils.isEmpty(asignments)) {
			return new ResponseEntity<>(asignments, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(asignments, HttpStatus.OK);		
	}

	@GetMapping("/springs/{sid}/asignments/{id}")
	public ResponseEntity<Asignment> getSpringById(@PathVariable("id") int id) {
		return new ResponseEntity<>(this.asignmentService.getAsignmentById(id), HttpStatus.OK);
	}

	@GetMapping("/springs/{sid}/asignments/features/{fid}")
	public ResponseEntity<Asignment> getAsignmentByFeatureId(@PathVariable("sid") int sid, @PathVariable("fid") int fid) {
		return new ResponseEntity<>(this.asignmentService.getAsignmentByFeatureId(fid), HttpStatus.OK);
	}
	
	@PostMapping(value = "/springs/{sid}/asignments")
	public ResponseEntity<Asignment> postAsignment(@PathVariable("sid") int sid,@RequestBody Asignment asignment) {
		try {
			return new ResponseEntity<>(this.asignmentService.save(asignment, sid), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.featureAlreadyAsignedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}

	@PutMapping("/springs/{sid}/asignments/{id}")
	public ResponseEntity<Asignment> updateAsignment(@PathVariable("sid") int sid,@PathVariable("id") int id, @RequestBody Asignment asignment) {
		try {
			return new ResponseEntity<>(this.asignmentService.update(id, asignment, sid),HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.featureAlreadyAsignedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}
	
	@DeleteMapping("/springs/{sid}/asignments/{id}")
	public ResponseEntity<Void> deleteAsignment(@PathVariable("sid") int sid, @PathVariable("id") int id) {
		this.asignmentService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/springs/{sid}/asignments")
	public ResponseEntity<Void> deleteAsignments(@PathVariable("sid") int sid) {
		this.asignmentService.deleteAllBySpringId(sid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
