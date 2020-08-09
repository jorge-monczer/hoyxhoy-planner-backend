package com.hhplanner.entities.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhplanner.entities.query.Q_OutlookAdvanceSummary;
import com.hhplanner.entities.service.OutlookService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class OutlookController extends BasicController {

	private final OutlookService outlookService;
	
	@Autowired
	public OutlookController(OutlookService outlookService) {
		this.outlookService = outlookService;
	}

	@GetMapping("/projects/{pid}/q_spring")
	public ResponseEntity<List<Q_OutlookAdvanceSummary>> getSumSpentGroupBySpring(@PathVariable("pid") int pid) {
		List<Q_OutlookAdvanceSummary> q_springs = this.outlookService.getSpentGroupBySpring(pid);
		if (CollectionUtils.isEmpty(q_springs)) {
			return new ResponseEntity<>(q_springs, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(q_springs, HttpStatus.OK);		
	}
	
}
