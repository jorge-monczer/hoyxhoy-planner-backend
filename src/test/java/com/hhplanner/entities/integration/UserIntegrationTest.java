package com.hhplanner.entities.integration;

import static com.hhplanner.utils.AssertUtils.assertObjectsEqual;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.util.IterableUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplanner.HoyxhoyPlannerBackendApplication;
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.UserService;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.UserBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@Transactional
@AutoConfigureMockMvc
public class UserIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService service;

	@Autowired
	private BuilderFactory builderFactory;

	private UserBuilder builder;

	@Before
	public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getUserBuilder();
	}
	
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getUser_WithUsername_ReturnsUser() throws Exception {
		User1 userInDB =this.builder.buildU1().save().getUser();
		ResultActions perform = this.mockMvc.perform(get("/api/users/{username}", userInDB.getUsername()));
		expectedPerform(perform,status().isOk(), userInDB,null);
	}

	@Test
	public void getUser_WithUsername_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/users/{username}", "supermario"))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.USER_NOT_FOUND));
	}
	
	@Test
	public void getUsers() throws Exception {
		List<User1> savedUsers = this.createUserListSavedToTest();
		ResultActions perform = this.mockMvc.perform(get("/api/users"));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedUsers.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedUsers.get(i), i);
		}
	}

	private List<User1> createUserListSavedToTest() {
		List<User1> users = new ArrayList<>();
		users.add(this.builder.buildU1().save().getUser());
		users.add(this.builder.buildU2().save().getUser());
		users.add(this.builder.buildU3().save().getUser());
		return users;
	}
	
	
	@Test
	public void getUsers_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/users"));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postUser_ReturnsUser() throws Exception {
		User1 userToSave = this.builder.buildU1().getUser();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users").content(asJsonString(userToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		User1 retrievedUser = this.service.getUserByUsername(userToSave.getUsername());
		expectedPerform(perform,status().isCreated(), retrievedUser,null);
	}

	@Test
	public void postUser_WithDuplicateUsername() throws Exception {
		User1 userInDB =this.builder.buildU1().save().getUser();
		User1 userToSave = this.builder.buildU2().getUser();
		userToSave.setUsername(userInDB.getUsername());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users").content(asJsonString(userToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.USER_ALREADY_EXISTS));
	}
	
	@Test
	public void putUser_WithUsername_ReturnsUser() throws Exception {
		User1 userInDB =this.builder.buildU1().save().getUser();
		userInDB.setPassword("123456");
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/users/{username}",userInDB.getUsername()).content(asJsonString(userInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		User1 retrievedUser = this.service.getUserByUsername(userInDB.getUsername());
		expectedPerform(perform,status().isOk(), retrievedUser,null);
	}

	@Test
	public void putUser_WithUsername_ChangeAllFields_ReturnsProject() throws Exception {
		User1 userInDB =this.builder.buildU1().save().getUser();
		User1 updatedUser = this.builder.buildU2().getUser();
		updatedUser.setUsername(userInDB.getUsername());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/users/{username}",userInDB.getUsername()).content(asJsonString(updatedUser))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		User1 retrievedUser = this.service.getUserByUsername(updatedUser.getUsername());
		expectedPerform(perform,status().isOk(), updatedUser,null);
		assertObjectsEqual(updatedUser, retrievedUser);
	}
	
	@Test
	public void putUser_WithUsername_NotFound() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/users/{username}","user1").content(asJsonString(this.builder.buildU1().getUser()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.USER_NOT_FOUND));
	}

	@Test
	public void deleteUser_WithUsername() throws Exception {
		User1 userInDB =this.builder.buildU1().save().getUser();
		ResultActions perform = this.mockMvc.perform(delete("/api/users/{username}", userInDB.getUsername()));
		perform.andExpect(status().isOk());
		Iterable<User1> users = this.service.getUsers();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(users));
	}

	@Test
	public void deleteUsers() throws Exception {
		this.createUserListSavedToTest();
		ResultActions perform = this.mockMvc.perform(delete("/api/users"));
		perform	.andExpect(status().isNoContent());
		Iterable<User1> users = this.service.getUsers();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(users));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, User1 project, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "username").value(project.getUsername()))
				.andExpect(jsonPath(arr + "password").value(project.getPassword()))
				;
	}

	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
