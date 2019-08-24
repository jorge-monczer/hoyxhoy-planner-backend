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
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.entities.service.SpringService;
import com.hhplanner.mockups.MockupProjectsToTest;
import com.hhplanner.mockups.MockupSpringsToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class SpringIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SpringService service;

	@Autowired
	private ProjectService projectService;
    private int pid;

    @Before
    public void init() {
		Project projectInDB = this.projectService.save(MockupProjectsToTest.createProjectTLMK(0));
    	this.pid = projectInDB.getId();
    }
    
	@After
	public void delecteAll() {
		this.service.deleteAll();
		this.projectService.delete(this.pid);
	}
	
	@Test
	public void getSpring_WithId_ReturnsSpring() throws Exception {
		Spring springInDB = this.service.save(MockupSpringsToTest.createSpringS1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs/{id}",this.pid, springInDB.getId()));
		expectedPerform(perform,status().isOk(), springInDB,null);
	}

	@Test
	public void getSpring_WithId_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/{pid}/springs/{id}",this.pid, 1))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getSpring_WithCode_ReturnsSpring() throws Exception {
		Spring springInDB =this.service.saveAndFlush(MockupSpringsToTest.createSpringS1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs/code/{code}", this.pid, springInDB.getCode()));
		expectedPerform(perform,status().isOk(), springInDB,null);
	}

	@Test
	public void getSpring_WithCode_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/{pid}/springs/code/{code}", this.pid, "S1"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getSpringsByProjectId() throws Exception {
		List<Spring> springListToSave = MockupSpringsToTest.createSpringsListToSaveTest();
//		int sizeProjectToSave = projectListToSave.size();
		List<Spring> savedSprings = new ArrayList<>();
		for (Spring springToSave : springListToSave) {
			Spring savedSpring =this.service.saveAndFlush(springToSave,this.pid);
			savedSprings.add(savedSpring);
		}
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs",this.pid));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedSprings.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedSprings.get(i), i);
		}
	}

	@Test
	public void getSpringsByProjectId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs",this.pid));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postSpringOfProjectId_ReturnsSpring() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/springs",this.pid).content(asJsonString(MockupSpringsToTest.createSpringS1(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spring retrievedProject = this.service.getSpringByCodeAndProjectId("S1",this.pid);
		expectedPerform(perform,status().isCreated(), retrievedProject,null);
	}

	@Test
	public void postSpringOfProjectId_WithDuplicateCode() throws Exception {
		Spring projectInDB =this.service.saveAndFlush(MockupSpringsToTest.createSpringS1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/springs",this.pid).content(asJsonString(MockupSpringsToTest.createSpringS1(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
		this.service.delete(projectInDB.getId());
	}
	
	@Test
	public void putSpring_WithId_ReturnsSpring() throws Exception {
		Spring springInDB =this.service.saveAndFlush(MockupSpringsToTest.createSpringS1(0),this.pid);
		springInDB.setSpringDays(30);
		springInDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",this.pid,springInDB.getId()).content(asJsonString(springInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spring retrievedSpring = this.service.getSpringById(springInDB.getId());
		expectedPerform(perform,status().isOk(), springInDB,null);
		assertObjectsEqual(springInDB, retrievedSpring);
	}

	@Test
	public void putSpring_WithId_ChangeAllFields_ReturnsSpring() throws Exception {
		Spring springInDB =this.service.saveAndFlush(MockupSpringsToTest.createSpringS1(0),this.pid);
		Spring updatedSpringS1 = MockupSpringsToTest.createSpringS2(springInDB.getId());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",this.pid,springInDB.getId()).content(asJsonString(updatedSpringS1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spring retrievedSpring = this.service.getSpringById(updatedSpringS1.getId());
		expectedPerform(perform,status().isOk(), updatedSpringS1,null);
		assertObjectsEqual(updatedSpringS1, retrievedSpring);
	}
	
	@Test
	public void putSpring_WithId_NotFound() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",1,1).content(asJsonString(MockupSpringsToTest.createSpringS1()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	public void putSpringOfProjectId_WithId_DuplicatedCode() throws Exception {
		Spring springS1InDB =this.service.save(MockupSpringsToTest.createSpringS1(0),this.pid);
		Spring springS2InDB =this.service.save(MockupSpringsToTest.createSpringS2(0),this.pid);
		springS1InDB.setCode(springS2InDB.getCode());
		springS1InDB.setStartDate(springS2InDB.getStartDate());		
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}", this.pid, springS1InDB.getId()).content(asJsonString(springS1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		Spring springInDB =this.service.save(MockupSpringsToTest.createSpringS1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/springs/{id}", this.pid, springInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Spring> springs = this.service.getSpringsByProjectId(this.pid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(springs));
	}

	@Test
	public void deleteProjects() throws Exception {
		List<Spring> springListToSave = MockupSpringsToTest.createSpringsListToSaveTest();
		List<Spring> savedSprings = new ArrayList<>();
		for (Spring springToSave : springListToSave) {
			Spring savedSpring =this.service.save(springToSave,this.pid);
			savedSprings.add(savedSpring);
		}
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/springs", this.pid));
		perform	.andExpect(status().isNoContent());
		Iterable<Spring> projects = this.service.getSpringsByProjectId(this.pid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Spring project, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id").value(project.getId()))
				.andExpect(jsonPath(arr + "code").value(project.getCode()))
				.andExpect(jsonPath(arr + "name").value(project.getName()))
				.andExpect(jsonPath(arr + "startDate").value(project.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)))
				.andExpect(jsonPath(arr + "endDate").value(project.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE)))
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
