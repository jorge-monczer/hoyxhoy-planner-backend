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
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.ProjectBuilder;

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
	
	@Autowired
	private BuilderFactory builderFactory;

	private ProjectBuilder builder;
	
	@Before
	public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getProjectBuilder();
	}
	
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getProject_WithId_ReturnsProject() throws Exception {
//		Project mockInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		Project mockInDB = this.builder.buildP1(0).save().getProject();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{id}", mockInDB.getId()));
		expectedPerform(perform,status().isOk(), mockInDB,null);
	}

	@Test
	public void getProject_WithId_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{id}", 1))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.PROJECT_NOT_FOUND));
	}
	
	@Test
	public void getProject_WithCode_ReturnsProject() throws Exception {
//		Project springInDB =this.service.save(MockupProjectsToTest.createProjectTLMK(0));
		Project mockInDB =this.builder.buildP1(0).save().getProject();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/code/{code}", mockInDB.getCode()));
		expectedPerform(perform,status().isOk(), mockInDB,null);
	}

	@Test
	public void getProject_WithCode_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/code/{code}", this.builder.buildP1(0).getProject().getCode()))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.PROJECT_NOT_FOUND));
	}
	
	@Test
	public void getProjects() throws Exception {
		List<Project> savedProjects = createProjectListSavedToTest();
		ResultActions perform = this.mockMvc.perform(get("/api/projects"));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedProjects.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedProjects.get(i), i);
		}
	}

	private List<Project> createProjectListSavedToTest() {
		List<Project> projects = new ArrayList<>();
		projects.add(this.builder.buildP1(0).save().getProject());
		projects.add(this.builder.buildP2(0).save().getProject());
		projects.add(this.builder.buildP3(0).save().getProject());
		return projects;
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
				MockMvcRequestBuilders.post("/api/projects").content(asJsonString(this.builder.buildP1(0).getProject()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedSpring = this.service.getProjectByCode(this.builder.getProject().getCode());
		expectedPerform(perform,status().isCreated(), retrievedSpring,null);
	}

	@Test
	public void postProject_WithDuplicateCode() throws Exception {
		this.builder.buildP1(0).save().getProject();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects").content(asJsonString(this.builder.buildP1(0).getProject()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.PROJECT_DUPLICATED));
	}
	
	@Test
	public void putProject_WithId_ReturnsProject() throws Exception {
		Project projectInDB =this.builder.buildP1(0).save().getProject();
		projectInDB.setSpringDays(30);
		projectInDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",projectInDB.getId()).content(asJsonString(projectInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedSpring = this.service.getProjectById(projectInDB.getId());
		expectedPerform(perform,status().isOk(), projectInDB,null);
		assertObjectsEqual(projectInDB, retrievedSpring);
	}

	@Test
	public void putProject_WithId_ChangeAllFields_ReturnsProject() throws Exception {
		Project projectInDB = this.builder.buildP1(0).save().getProject();
		Project updatedProjectP2 = this.builder.buildP2(projectInDB.getId()).getProject();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",projectInDB.getId()).content(asJsonString(updatedProjectP2))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Project retrievedProject = this.service.getProjectById(updatedProjectP2.getId());
		expectedPerform(perform,status().isOk(), updatedProjectP2,null);
		assertObjectsEqual(updatedProjectP2, retrievedProject);
	}
	
	
	@Test
	public void putProject_WithId_NotFound() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",1).content(asJsonString(this.builder.buildP1(1).getProject()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.PROJECT_NOT_FOUND));
	}

	@Test
	public void putProject_WithId_DuplicatedCode() throws Exception {
		Project projectP1InDB =this.builder.buildP1(0).save().getProject();
		Project projectP2InDB =this.builder.buildP2(0).save().getProject();
		projectP1InDB.setCode(projectP2InDB.getCode());
		projectP1InDB.setSpringDays(30);
		projectP1InDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{id}",projectP1InDB.getId()).content(asJsonString(projectP1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.PROJECT_DUPLICATED));
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		Project springInDB =this.builder.buildP1(0).save().getProject();
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{id}", springInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Project> springs = this.service.getProjects();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(springs));
	}

	@Test
	public void deleteProject() throws Exception {
		this.createProjectListSavedToTest();
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
