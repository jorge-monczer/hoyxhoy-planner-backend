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
import com.hhplanner.entities.model.Spending;
import com.hhplanner.entities.service.SpendingService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class SpendingController extends BasicController {

	private final SpendingService spendingService;
	
	@Autowired
	public SpendingController(SpendingService spendingService) {
		this.spendingService = spendingService;
	}
	
	@GetMapping("/asignments/{aid}/spendings")
	public ResponseEntity<Iterable<Spending>> getAllSpendingsByAsignmentID(@PathVariable("aid") int aid) {
		Iterable<Spending> spendings = this.spendingService.getSpendingsByAsignmentId(aid);
		if (CollectionUtils.isEmpty(spendings)) {
			return new ResponseEntity<>(spendings, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(spendings, HttpStatus.OK);		
	}

	@GetMapping("/asignments/{aid}/spendings/{day}")
	public ResponseEntity<Spending> getSpendingByDay(@PathVariable("aid") int aid, @PathVariable("day") int day) {
		return new ResponseEntity<>(this.spendingService.getSpendingById(aid,day), HttpStatus.OK);
	}

	@PostMapping(value = "/asignments/{aid}/spendings/{day}")
	public ResponseEntity<Spending> postSpending(@PathVariable("aid") int aid,@PathVariable("day") int day,@RequestBody Spending spending) {
		try {
			return new ResponseEntity<>(this.spendingService.save(spending, aid, day), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.spendingDuplicatedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}

	@PutMapping("/asignments/{aid}/spendings/{day}")
	public ResponseEntity<Spending> updateSpending(@PathVariable("aid") int aid,@PathVariable("day") int day, @RequestBody Spending spending) {
		try {
			return new ResponseEntity<>(this.spendingService.update(spending, aid, day),HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.spendingNotFoundException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}
	
	@DeleteMapping("/asignments/{aid}/spendings/{day}")
	public ResponseEntity<Void> deleteSpending(@PathVariable("aid") int aid, @PathVariable("day") int day) {
		this.spendingService.delete(aid,day);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/asignments/{aid}/spendings")
	public ResponseEntity<Void> deleteSpendings(@PathVariable("aid") int aid) {
		this.spendingService.deleteByAsignmentId(aid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
