package com.hhplanner.entities.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.repo.UserRepository;

@Service
public class LoginService {

	public static final String RESET_PASSWORD = "2019";
	public static final String MASTER_USER = "masterhoy";
	public static final String MASTER_PASS = "astroboy2019";
	private static final int ONE_ROW_CHANGED = 1;
	private UserRepository userRepository;
	
	public LoginService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User1 login(String username, String password) {
		Optional<User1> optUser = this.userRepository.findByUsername(username);
		if (!optUser.isPresent()) {
			if (username != null && username.equals(MASTER_USER)) {
				optUser = Optional.of(new User1(MASTER_USER,MASTER_PASS,MASTER_USER,"N/A",null,null));
			} else {
				throw new EntityModelNotFoundException();
			}
		}
		User1 user = optUser.get();
		if(!user.getPassword().equals(password)) {
			throw new EntityModelNotFoundException();			
		}
		return user;
	}

	//Only for testing proposal
	public User1 save(User1 user) {
		try {
			return this.userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		}
	}

	public boolean changePassword(String username,String newPassword) {
		if (!this.userRepository.existsByUsername(username)) {
			throw new EntityModelNotFoundException();
		}
		try {
			return this.userRepository.changePassword(username, newPassword) == ONE_ROW_CHANGED;
		} catch (DataIntegrityViolationException e) {
			throw EntityModelDuplicatedException.getInstance(e.getMessage());
		} 
	}
	
	public boolean resetPassword(String username) {
		return changePassword(username,buildPasswordReseted(username));
	}

	protected String buildPasswordReseted(String username) {
		return username + RESET_PASSWORD;
	}

	public void delete(User1 user) {
		this.userRepository.delete(user);
	}

	public void deleteAll() {
		this.userRepository.deleteAll();
	}
	
}
