package com.hhplanner.entities.integration;

import static com.hhplanner.utils.AssertUtils.assertObjectsEqual;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.IterableUtil;
import org.junit.After;
import org.junit.Assert;
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
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.mockups.MockupProjectsToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class ProjectIntegrationTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ProjectService service;

	@After
	public void delecteAll() {
		this.service.deleteAll();
	}
	
	@Test
	public void getProject_WithId_ReturnsProject() throws Exception {
		Project mockInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{id}", mockInDB.getId()));
		expectedPerform(perform,status().isOk(), mockInDB,null);
	}

	@Test
	public void getProject_WithId_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/{id}", 1))
				.andExpect(status().isNotFound());
	}
	
	public void getProject_WithCode_ReturnsProject() throws Exception {
		Project springInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(get("/api/projects/code/{code}", springInDB.getCode()));
		expectedPerform(perform,status().isOk(), springInDB,null);
	}

	@Test
	public void getProject_WithCode_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/code/{code}", MockupProjectsToTest.createProjectTLMK(0).getCode()))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getProjects() throws Exception {
		List<Project> springsListToSave = MockupProjectsToTest.createProjectListToSaveTest();
//		int sizeProjectToSave = projectListToSave.size();
		List<Project> savedSprings = new ArrayList<>();
		for (Project springToSave : springsListToSave) {
			Project savedProject =this.service.save(springToSave);
			savedSprings.add(savedProject);
		}
		ResultActions perform = this.mockMvc.perform(get("/api/projects"));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedSprings.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedSprings.get(i), i);
		}
	}

	@Test
	public void getProject_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects"));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postProject_ReturnsProject() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects").content(asJsonString(MockupProjectsToTest.createProjectTLMK(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedSpring = this.service.getProjectByCode(MockupProjectsToTest.createProjectTLMK(0).getCode());
		expectedPerform(perform,status().isCreated(), retrievedSpring,null);
	}

	@Test
	public void postProject_WithDuplicateCode() throws Exception {
		this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects").content(asJsonString(MockupProjectsToTest.createProjectTLMK(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void putProject_WithId_ReturnsProject() throws Exception {
		Project springInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		springInDB.setSpringDays(30);
		springInDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",springInDB.getId()).content(asJsonString(springInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedSpring = this.service.getProjectById(springInDB.getId());
		expectedPerform(perform,status().isOk(), springInDB,null);
		assertObjectsEqual(springInDB, retrievedSpring);
	}

	@Test
	public void putProject_WithId_ChangeAllFields_ReturnsProject() throws Exception {
		Project springInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		Project updatedSpringS2 = MockupProjectsToTest.createProjectTLMK2(springInDB.getId());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",springInDB.getId()).content(asJsonString(updatedSpringS2))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedSpring = this.service.getProjectById(updatedSpringS2.getId());
		expectedPerform(perform,status().isOk(), updatedSpringS2,null);
		assertObjectsEqual(updatedSpringS2, retrievedSpring);
	}
	
	
	@Test
	public void putProject_WithId_NotFound() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",1).content(asJsonString(MockupProjectsToTest.createProjectTLMK()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	public void putProject_WithId_DuplicatedCode() throws Exception {
		Project springS1InDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		Project springS2InDB =this.service.save(MockupProjectsToTest.createProjectTLMK2(0));
		springS1InDB.setCode(springS2InDB.getCode());
		springS1InDB.setSpringDays(30);
		springS1InDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",springS1InDB.getId()).content(asJsonString(springS1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		Project springInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{id}", springInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Project> springs = this.service.getProjects();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(springs));
	}

	@Test
	public void deleteProject() throws Exception {
		List<Project> springListToSave = MockupProjectsToTest.createProjectListToSaveTest();;
		List<Project> savedSprings = new ArrayList<>();
		for (Project springToSave : springListToSave) {
			Project savedSpring =this.service.save(springToSave);
			savedSprings.add(savedSpring);
		}
		ResultActions perform = this.mockMvc.perform(delete("/api/projects"));
		perform	.andExpect(status().isNoContent());
		Iterable<Project> projects = this.service.getProjects();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
	}

	protected void expectedPerform(ResultActions perform, ResultMatcher status, Project proj, Integer idx)
			throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id").value(proj.getId()))
				.andExpect(jsonPath(arr + "code").value(proj.getCode()))
				.andExpect(jsonPath(arr + "name").value(proj.getName()))
				.andExpect(jsonPath(arr + "startDate").value(proj.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)))
				.andExpect(jsonPath(arr + "springDays").value(proj.getSpringDays()))
				;
	}
	
	protected String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
