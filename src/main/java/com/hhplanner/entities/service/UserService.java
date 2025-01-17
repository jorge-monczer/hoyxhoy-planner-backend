package com.hhplanner.entities.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.repo.UserRepository;

@Service
@Transactional
public class UserService {

	private UserRepository userRepository;
	private LoginService loginService;
	
	public UserService(UserRepository userRepository, LoginService loginService) {
		this.userRepository = userRepository;
		this.loginService = loginService;
	}

	public User1 getUserByUsername(String username) {
		Optional<User1> user = this.userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw BusinessExceptionFactory.userNotFoundException();
		}
		return user.get();
	}

	public Iterable<User1> getUsers() {
		return this.userRepository.findAll();
	}

	public User1 save(User1 user) {
		if (this.userRepository.existsByUsername(user.getUsername())) {
			throw BusinessExceptionFactory.userAlreadyExistsException();
		}
		user.setPassword(this.loginService.buildPasswordReseted(user.getUsername()));
		return this.userRepository.save(user);
	}

	public User1 saveAndFlush(User1 user) {
		User1 save = save(user);
		this.userRepository.flush();
		return save;
	}
	
	public User1 update(String username,User1 user) {
		if (!this.userRepository.existsByUsername(username)) {
			throw BusinessExceptionFactory.userNotFoundException();
		}
		user.setUsername(username);
		return this.userRepository.save(user);
	}

	public void delete(String username) {
		this.userRepository.deleteByUsername(username);
	}

	public void deleteAll() {
		this.userRepository.deleteAll();
	}

}
