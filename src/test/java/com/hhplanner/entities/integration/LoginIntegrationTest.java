package com.hhplanner.entities.integration;

import static com.hhplanner.entities.service.LoginService.RESET_PASSWORD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.After;
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
import com.hhplanner.entities.model.User1;
import com.hhplanner.entities.service.LoginService;
import com.hhplanner.mockups.MockupUsersToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@Transactional
@AutoConfigureMockMvc
public class LoginIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private LoginService service;

	@After
	public void delecteAll() {
		this.service.deleteAll();
	}
	
	@Test
	public void loginUser_WithUserName_ReturnsUser() throws Exception {
		User1 userInDB =this.service.save(MockupUsersToTest.createUser1());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",userInDB.getUsername()).content(userInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		expectedPerform(perform,status().isOk(), userInDB,null);
	}

	@Test
	public void loginUser_WithUserName_NotFound_Returns404() throws Exception {
		this.service.save(MockupUsersToTest.createUser1());
		User1 userNotInDB = MockupUsersToTest.createUser2();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",userNotInDB.getUsername()).content(userNotInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
	}

	@Test
	public void loginUser_WithUserName_InvalidPassword_Returns404() throws Exception {
		User1 userInDB =this.service.save(MockupUsersToTest.createUser1());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",userInDB.getUsername()).content("InvalidPass")
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
	}

	@Test
	public void loginUser_WithUserMasterHoy_ReturnsMasterUser() throws Exception {
		this.service.save(MockupUsersToTest.createUser1());
		User1 masterUser = MockupUsersToTest.createUserMasterHoy();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",masterUser.getUsername()).content(masterUser.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		expectedPerform(perform,status().isOk(), masterUser,null);
	}

	@Test
	public void loginUser_WithUserMasterHoy_InvalidPassword_Returns404() throws Exception {
		this.service.save(MockupUsersToTest.createUser1());
		User1 masterUser = MockupUsersToTest.createUserMasterHoy();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",masterUser.getUsername()).content("InvalidPass")
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
	}

	@Test
	public void loginUser_WithUserMasterHoyInDB_ReturnsMasterUser() throws Exception {
		User1 masterUser = MockupUsersToTest.createUserMasterHoy();
		masterUser.setPassword("masterUserChangedPass");
		User1 userInDB =this.service.save(masterUser);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",userInDB.getUsername()).content(userInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		expectedPerform(perform,status().isOk(), userInDB,null);
	}

	@Test
	public void loginUser_WithUserMasterHoyInDB_InvalidPassword_Returns404() throws Exception {
		User1 masterUser = MockupUsersToTest.createUserMasterHoy();
		masterUser.setPassword("masterUserChangedPass");
		User1 userInDB =this.service.save(masterUser);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",userInDB.getUsername()).content(MockupUsersToTest.createUserMasterHoy().getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
	}

	@Test
	public void changePassword_WithUserName_ReturnsTrue() throws Exception {
		User1 userInDB =this.service.save(MockupUsersToTest.createUser1());
		userInDB.setPassword("userChangedPass");
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/change-password/{user-name}",userInDB.getUsername()).content(userInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").value(Boolean.TRUE));
	}

	@Test
	public void changePassword_WithUserName_NotFound_Returns404() throws Exception {
		this.service.save(MockupUsersToTest.createUser1());
		User1 userNotInDB = MockupUsersToTest.createUser2();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/change-password/{user-name}",userNotInDB.getUsername()).content(userNotInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
	}

	@Test
	public void changePassword_WithUserMasterHoyInDB_ReturnsMasterUser() throws Exception {
		User1 userInDB =this.service.save(MockupUsersToTest.createUserMasterHoy());
		userInDB.setPassword("masterUserChangedPass");
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/change-password/{user-name}",userInDB.getUsername()).content(userInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").value(Boolean.TRUE));
	}

	@Test
	public void changePassword_WithUserMasterHoy_NotFound_Returns404() throws Exception {
		this.service.save(MockupUsersToTest.createUser1());
		User1 userNotInDB = MockupUsersToTest.createUserMasterHoy();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/change-password/{user-name}",userNotInDB.getUsername()).content(userNotInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
	}

	@Test
	public void resetPassword_WithUserName_ReturnsTrue() throws Exception {
		User1 userInDB =this.service.save(MockupUsersToTest.createUser1());
		ResultActions perform = this.mockMvc.perform(get("/api/login/reset-password/{user-name}", userInDB.getUsername()));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").value(Boolean.TRUE));
		// login with new password
		userInDB.setPassword(RESET_PASSWORD);
		ResultActions perform2 = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/login/{user-name}",userInDB.getUsername()).content(userInDB.getPassword())
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		expectedPerform(perform2,status().isOk(), userInDB,null);
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, User1 user, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "username").value(user.getUsername()))
				.andExpect(jsonPath(arr + "password").value(user.getPassword()))
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
