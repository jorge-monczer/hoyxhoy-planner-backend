package com.hhplanner.entities.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplanner.entities.exception.EntityModelDuplicatedException;
import com.hhplanner.entities.exception.EntityModelNotFoundException;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.mockups.MockupsToTest;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService projectService;
	
	@Test
	public void getProject_WithId_ReturnsProject() throws Exception {
		Project projectTLMK = MockupsToTest.createProjectTLMK();
		when(this.projectService.getProjectById(anyInt())).thenReturn(projectTLMK);
		ResultActions perform = this.mockMvc.perform(get("/api/project/{id}", 1));
		expectedPerform(perform,status().isOk(), projectTLMK,null);
	}

	@Test
	public void getProyect_WithId_NotFound_Returns404() throws Exception {
		when(this.projectService.getProjectById(anyInt())).thenThrow(new EntityModelNotFoundException());
		this.mockMvc.perform(get("/api/project/{id}", 1))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getProject_WithCode_ReturnsProject() throws Exception {
		Project projectTLMK = MockupsToTest.createProjectTLMK();
		when(this.projectService.getProjectByCode(anyString())).thenReturn(projectTLMK);
		ResultActions perform = this.mockMvc.perform(get("/api/project/code/{code}", "TLMK"));
		expectedPerform(perform,status().isOk(), projectTLMK,null);
	}

	@Test
	public void getProyect_WithCode_NotFound_Returns404() throws Exception {
		when(this.projectService.getProjectByCode(anyString())).thenThrow(new EntityModelNotFoundException());
		this.mockMvc.perform(get("/api/project/code/{code}", "TLMK"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getProjects() throws Exception {
		List<Project> projectList = MockupsToTest.createProjectListToTest();
		when(this.projectService.getProjects()).thenReturn(projectList);
		ResultActions perform = this.mockMvc.perform(get("/api/projects"));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < projectList.size(); i++) {
        	expectedPerform(perform,status().isOk(), projectList.get(i), i);
		}
	}

	@Test
	public void getProjects_ReturnsNoContent() throws Exception {
		when(this.projectService.getProjects()).thenReturn(new ArrayList<Project>());
		ResultActions perform = this.mockMvc.perform(get("/api/projects"));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postProject_ReturnsProject() throws Exception {
		Project projectTLMK = MockupsToTest.createProjectTLMK();
		when(this.projectService.save(any(Project.class))).thenReturn(projectTLMK);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/project").content(asJsonString(MockupsToTest.createProjectTLMK(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		expectedPerform(perform,status().isCreated(), projectTLMK,null);
	}

	@Test
	public void postProject_WithDuplicateCode() throws Exception {
		when(this.projectService.save(any(Project.class))).thenThrow(EntityModelDuplicatedException.getInstance("msg"));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/project").content(asJsonString(MockupsToTest.createProjectTLMK(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void putProject_WithId_ReturnsProject() throws Exception {
		Project updatedProjectTLMK = MockupsToTest.createProjectTLMK2(1);
		when(this.projectService.update(anyInt(),any(Project.class))).thenReturn(updatedProjectTLMK);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/project/{id}",1).content(asJsonString(MockupsToTest.createProjectTLMK()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		expectedPerform(perform,status().isOk(), updatedProjectTLMK,null);
	}

	@Test
	public void putProject_WithId_NotFound() throws Exception {
		when(this.projectService.update(anyInt(),any(Project.class))).thenThrow(new EntityModelNotFoundException());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/project/{id}",1).content(asJsonString(MockupsToTest.createProjectTLMK()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	public void putProject_WithId_DuplicatedCode() throws Exception {
		when(this.projectService.update(anyInt(),any(Project.class))).thenThrow(EntityModelDuplicatedException.getInstance("msg"));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/project/{id}",1).content(asJsonString(MockupsToTest.createProjectTLMK()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		ResultActions perform = this.mockMvc.perform(delete("/api/project/{id}", 1));
		perform.andExpect(status().isOk());
	}

	@Test
	public void deleteProjects() throws Exception {
		ResultActions perform = this.mockMvc.perform(delete("/api/projects"));
		perform	.andExpect(status().isNoContent());
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
