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

import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.UserService;
import com.hhplanner.utils.CollectionUtils;

@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<Iterable<User1>> getAllUsers() {
		Iterable<User1> userstar = this.userService.getUsers();
		if (CollectionUtils.isEmpty(userstar)) {
			return new ResponseEntity<>(userstar, HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<>(userstar, HttpStatus.OK);		
	}

	@GetMapping("/users/{username}")
	public ResponseEntity<User1> getUsertByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<>(this.userService.getUserByUsername(username), HttpStatus.OK);
	}

	@PostMapping(value = "/users")
	public ResponseEntity<User1> postUser(@RequestBody User1 user) {
		return new ResponseEntity<>(this.userService.save(user), HttpStatus.CREATED);
	}

	@PutMapping("/users/{username}")
	public ResponseEntity<User1> updateProject(@PathVariable("username") String username, @RequestBody User1 user) {
		return new ResponseEntity<>(this.userService.update(username, user),HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		this.userService.delete(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<Void> deleteUsers() {
		this.userService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
