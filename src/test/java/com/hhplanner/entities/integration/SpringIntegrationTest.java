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
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.SpringService;
import com.hhplanner.mockups.AsignmentBuilder;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.SpringBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class SpringIntegrationTest {
	private static int ANY_PROJEC_ID = 1233333;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SpringService service;

	@Autowired
	private BuilderFactory builderFactory;

	SpringBuilder builder;
 
	@Before
	public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getSpringBuilder();
	}
	
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getSpring_WithId_ReturnsSpring() throws Exception {
		Spring springInDB = this.builder.buildP1().buildS1(0).save().getSpring();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs/{id}",this.builder.getProjectId(), springInDB.getId()));
		expectedPerform(perform,status().isOk(), springInDB,null);
	}

	@Test
	public void getSpring_WithId_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs/{id}",ANY_PROJEC_ID, 1))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPRING_NOT_FOUND));
	}
	
	@Test
	public void getSpring_WithCode_ReturnsSpring() throws Exception {
		Spring springInDB = this.builder.buildP1().buildS1(0).save().getSpring();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs/code/{code}", this.builder.getProjectId(), springInDB.getCode()));
		expectedPerform(perform,status().isOk(), springInDB,null);
	}

	@Test
	public void getSpring_WithCode_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs/code/{code}", ANY_PROJEC_ID, "S1"))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPRING_NOT_FOUND));
	}
	
	@Test
	public void getSpringsByProjectId() throws Exception {
		List<Spring> savedSprings = this.createSpringListSavedTest();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs",this.builder.getProjectId()));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedSprings.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedSprings.get(i), i);
		}
	}

	private List<Spring> createSpringListSavedTest() {
		List<Spring> projects = new ArrayList<>();
		projects.add(this.builder.buildP1().buildS1(0).save().getSpring());
		projects.add(this.builder.buildS2(0).save().getSpring());
		projects.add(this.builder.buildS3(0).save().getSpring());
		return projects;
	}
	
	
	@Test
	public void getSpringsByProjectId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/springs",ANY_PROJEC_ID));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postSpringOfProjectId_ReturnsSpring() throws Exception {
		Spring springS1 = this.builder.buildP1().buildS1(0).getSpring();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/springs",this.builder.getProjectId()).content(asJsonString(springS1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spring retrievedProject = this.service.getSpringByCodeAndProjectId(springS1.getCode(),this.builder.getProjectId());
		expectedPerform(perform,status().isCreated(), retrievedProject,null);
	}

	@Test
	public void postSpringOfProjectId_WithDuplicateCode() throws Exception {
		this.builder.buildP1().buildS1(0).save().getSpring();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/springs",this.builder.getProjectId()).content(asJsonString(this.builder.buildS1(0).getSpring()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPRING_DUPLICATED));
	}
	
	@Test
	public void putSpring_WithId_ReturnsSpring() throws Exception {
		Spring springInDB = this.builder.buildP1().buildS1(0).save().getSpring();
		springInDB.setSpringDays(30);
		springInDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",this.builder.getProjectId(),springInDB.getId()).content(asJsonString(springInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spring retrievedSpring = this.service.getSpringById(springInDB.getId());
		expectedPerform(perform,status().isOk(), springInDB,null);
		assertObjectsEqual(springInDB, retrievedSpring);
	}

	@Test
	public void putSpringWithAsignments_WithId_ReturnsSpring() throws Exception {
		Spring springInDB = this.builder.buildP1().buildS1(0).save().getSpring();
		AsignmentBuilder asbuilder = this.builderFactory.getAsignmentBuilder();
		asbuilder.buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		springInDB.setSpringDays(30);
		springInDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",this.builder.getProjectId(),springInDB.getId()).content(asJsonString(springInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spring retrievedSpring = this.service.getSpringById(springInDB.getId());
		expectedPerform(perform,status().isOk(), springInDB,null);
		assertObjectsEqual(springInDB, retrievedSpring);
		asbuilder.deleteAll();
	}
	
	@Test
	public void putSpring_WithId_ChangeAllFields_ReturnsSpring() throws Exception {
		Spring springInDB = this.builder.buildP1().buildS1(0).save().getSpring();
		Spring updatedSpringS1 = this.builder.buildS2(springInDB.getId()).getSpring();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",this.builder.getProjectId(),springInDB.getId()).content(asJsonString(updatedSpringS1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spring retrievedSpring = this.service.getSpringById(updatedSpringS1.getId());
		expectedPerform(perform,status().isOk(), updatedSpringS1,null);
		assertObjectsEqual(updatedSpringS1, retrievedSpring);
	}
	
	@Test
	public void putSpring_WithId_NotFound() throws Exception {
		this.builder.buildP1();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",this.builder.getProjectId(),1).content(asJsonString(this.builder.buildS1(1).getSpring()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPRING_NOT_FOUND));
	}

	@Test
	public void putSpringOfProjectId_WithId_DuplicatedCode() throws Exception {
		Spring springS1InDB = this.builder.buildP1().buildS1(0).save().getSpring();
		Spring springS2InDB = this.builder.buildS2(0).save().getSpring();
		springS1InDB.setCode(springS2InDB.getCode());
		springS1InDB.setStartDate(springS2InDB.getStartDate());		
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}", this.builder.getProjectId(), springS1InDB.getId()).content(asJsonString(springS1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPRING_DUPLICATED));
	}
	
	@Test
	public void putSpringWithAsignments_WithId_SpringDaysInsufficient() throws Exception {
		Spring springInDB = this.builder.buildP1().buildS1(0).save().getSpring();
		AsignmentBuilder asbuilder = this.builderFactory.getAsignmentBuilder();
		asbuilder.buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		springInDB.setSpringDays(4);
		springInDB.setStartDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/springs/{id}",this.builder.getProjectId(),springInDB.getId()).content(asJsonString(springInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPRING_DAYS_INSUFFICIENT));
		Spring retrievedSpring = this.service.getSpringById(springInDB.getId());
		assertObjectsEqual(this.builder.buildS1(springInDB.getId()).getSpring(), retrievedSpring);
		asbuilder.deleteAll();
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		Spring springInDB = this.builder.buildP1().buildS1(0).save().getSpring();
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/springs/{id}", this.builder.getProjectId(), springInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Spring> springs = this.service.getSpringsByProjectId(this.builder.getProjectId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(springs));
	}

	@Test
	public void deleteProjects() throws Exception {
		this.createSpringListSavedTest();
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/springs", this.builder.getProjectId()));
		perform	.andExpect(status().isNoContent());
		Iterable<Spring> projects = this.service.getSpringsByProjectId(this.builder.getProjectId());
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
