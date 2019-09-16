package com.hhplanner.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController extends BasicController {

	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PutMapping("/login/{user-name}")
	public ResponseEntity<User1> loginUser(@PathVariable("user-name") String userName, @RequestBody String password) {
		return new ResponseEntity<>(this.loginService.login(userName, password),HttpStatus.OK);
	}

	@PutMapping("/login/change-password/{user-name}")
	public ResponseEntity<Boolean> changePassword(@PathVariable("user-name") String userName, @RequestBody String newPassword) {
		return new ResponseEntity<>(this.loginService.changePassword(userName, newPassword),HttpStatus.OK);
	}
	
	@GetMapping("/login/reset-password/{user-name}")
	public ResponseEntity<Boolean> resetPassword(@PathVariable("user-name") String userName) {
		return new ResponseEntity<>(this.loginService.resetPassword(userName),HttpStatus.OK);
	}

}
