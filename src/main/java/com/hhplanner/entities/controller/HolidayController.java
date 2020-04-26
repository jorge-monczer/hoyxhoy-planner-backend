package com.hhplanner.entities.controller;

import java.time.LocalDate;

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
import com.hhplanner.entities.model.Holiday;
import com.hhplanner.entities.service.HolidayService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class HolidayController extends BasicController {

	private final HolidayService holidayService;
	
	@Autowired
	public HolidayController(HolidayService projectService) {
		this.holidayService = projectService;
	}
	
	@GetMapping("/holidays")
	public ResponseEntity<Iterable<Holiday>> getAllHolidays() {
		Iterable<Holiday> holidays = this.holidayService.getHolidays();
		if (CollectionUtils.isEmpty(holidays)) {
			return new ResponseEntity<>(holidays, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(holidays, HttpStatus.OK);		
	}

	@GetMapping("/holidays/{date}")
	public ResponseEntity<Holiday> getHolidayByDate(@PathVariable("date") String date) {
		return new ResponseEntity<>(this.holidayService.getHolidayByDate(LocalDate.parse(date)), HttpStatus.OK);
	}

	@PostMapping(value = "/holidays")
	public ResponseEntity<Holiday> postHolidays(@RequestBody Holiday holiday) {
		try {
			return new ResponseEntity<>(this.holidayService.save(holiday), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.holidayDuplicatedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}

	@PostMapping(value = "/holidays/{years}")
	public ResponseEntity<Iterable<Holiday>> postHolidaysForYears(@PathVariable("years") int years, @RequestBody Holiday holiday) {
		try {
			return new ResponseEntity<>(this.holidayService.saveForYears(holiday,years), HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.holidayDuplicatedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}
	
	
	@PutMapping("/holidays/{date}")
	public ResponseEntity<Holiday> updateProject(@PathVariable("date") String date, @RequestBody Holiday holiday) {
		try {
			return new ResponseEntity<>(this.holidayService.update(LocalDate.parse(date), holiday),HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw BusinessExceptionFactory.holidayDuplicatedException();
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw BusinessExceptionFactory.businessException(e.getMessage());
		}
	}
	
	@DeleteMapping("/holidays/{date}")
	public ResponseEntity<Void> deleteProject(@PathVariable("date") String date) {
		this.holidayService.delete(LocalDate.parse(date));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/holidays")
	public ResponseEntity<Void> deleteHolidays() {
		this.holidayService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
