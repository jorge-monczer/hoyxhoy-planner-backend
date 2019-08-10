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
	private MockMvc mockMvc;

	@Autowired
	private ProjectService service;

	@After
	public void delecteAll() {
		this.service.deleteAll();
	}
	
	@Test
	public void getProject_WithId_ReturnsProject() throws Exception {
		Project projectInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{id}", projectInDB.getId()));
		expectedPerform(perform,status().isOk(), projectInDB,null);
	}

	@Test
	public void getProyect_WithId_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/{id}", 1))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getProject_WithCode_ReturnsProject() throws Exception {
		Project projectInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(get("/api/projects/code/{code}", projectInDB.getCode()));
		expectedPerform(perform,status().isOk(), projectInDB,null);
	}

	@Test
	public void getProyect_WithCode_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/code/{code}", "TLMK"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getProjects() throws Exception {
		List<Project> projectListToSave = MockupProjectsToTest.createProjectListToSaveTest();
//		int sizeProjectToSave = projectListToSave.size();
		List<Project> savedProjects = new ArrayList<>();
		for (Project projectToSave : projectListToSave) {
			Project savedProject =this.service.save(projectToSave);
			savedProjects.add(savedProject);
		}
		ResultActions perform = this.mockMvc.perform(get("/api/projects"));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedProjects.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedProjects.get(i), i);
		}
	}

	@Test
	public void getProjects_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects"));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postProject_ReturnsProject() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects").content(asJsonString(MockupProjectsToTest.createProjectTLMK(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedProject = this.service.getProjectByCode("TLMK");
		expectedPerform(perform,status().isCreated(), retrievedProject,null);
	}

	@Test
	public void postProject_WithDuplicateCode() throws Exception {
		Project projectInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects").content(asJsonString(MockupProjectsToTest.createProjectTLMK(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
		this.service.delete(projectInDB.getId());
	}
	
	@Test
	public void putProject_WithId_ReturnsProject() throws Exception {
		Project projectInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		projectInDB.setSpringDays(30);
		projectInDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",projectInDB.getId()).content(asJsonString(projectInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedProject = this.service.getProjectById(projectInDB.getId());
		expectedPerform(perform,status().isOk(), projectInDB,null);
		assertObjectsEqual(projectInDB, retrievedProject);
	}

	@Test
	public void putProject_WithId_ChangeAllFields_ReturnsProject() throws Exception {
		Project projectInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		Project updatedProjectTLMK = MockupProjectsToTest.createProjectTLMK2(projectInDB.getId());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",projectInDB.getId()).content(asJsonString(updatedProjectTLMK))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedProject = this.service.getProjectById(updatedProjectTLMK.getId());
		expectedPerform(perform,status().isOk(), updatedProjectTLMK,null);
		assertObjectsEqual(updatedProjectTLMK, retrievedProject);
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
		Project projectTLMKInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		Project projectTLMK2InDB =this.service.save(MockupProjectsToTest.createProjectTLMK2(0));
		projectTLMKInDB.setCode(projectTLMK2InDB.getCode());
		projectTLMKInDB.setStartDate(projectTLMK2InDB.getStartDate());		
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",projectTLMKInDB.getId()).content(asJsonString(projectTLMKInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		Project projectInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{id}", projectInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Project> projects = this.service.getProjects();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
	}

	@Test
	public void deleteProjects() throws Exception {
		List<Project> projectListToSave = MockupProjectsToTest.createProjectListToSaveTest();
		List<Project> savedProjects = new ArrayList<>();
		for (Project projectToSave : projectListToSave) {
			Project savedProject =this.service.save(projectToSave);
			savedProjects.add(savedProject);
		}
		ResultActions perform = this.mockMvc.perform(delete("/api/projects"));
		perform	.andExpect(status().isNoContent());
		Iterable<Project> projects = this.service.getProjects();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Project project, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id").value(project.getId()))
				.andExpect(jsonPath(arr + "code").value(project.getCode()))
				.andExpect(jsonPath(arr + "name").value(project.getName()))
				.andExpect(jsonPath(arr + "startDate").value(project.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)))
				.andExpect(jsonPath(arr + "springDays").value(project.getSpringDays()))
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
