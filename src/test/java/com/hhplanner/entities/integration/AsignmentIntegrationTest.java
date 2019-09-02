package com.hhplanner.entities.integration;

import static com.hhplanner.utils.AssertUtils.assertObjectsEqual;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.AsignmentService;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.entities.service.SpringService;
import com.hhplanner.mockups.MockupAsignmentsToTest;
import com.hhplanner.mockups.MockupProjectsToTest;
import com.hhplanner.mockups.MockupSpringsToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class AsignmentIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AsignmentService service;

	@Autowired
	private ProjectService projectService;
	@Autowired
	private SpringService springService;
	
	@Autowired
	MockupAsignmentsToTest mockupAsignmentsToTest;

	private int pid;
	private int sid;

    @Before
    public void init() {
		Project projectInDB = this.projectService.saveAndFlush(MockupProjectsToTest.createProjectTLMK(0));
		pid = projectInDB.getId();
		Spring springInDB = this.springService.saveAndFlush(MockupSpringsToTest.createSpringS1(0),pid);
		sid = springInDB.getId();
    }
    
	@After
	public void delecteAll() {
		this.service.deleteAll();
		this.mockupAsignmentsToTest.deleteAll();
		this.springService.delete(this.sid);
		this.projectService.delete(this.pid);
	}
	
	@Test
	public void getAsignment_WithId_ReturnsAsignment() throws Exception {
		Asignment asignmentInDB = this.service.saveAndFlush(this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid),this.sid);
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments/{id}",this.sid, asignmentInDB.getId()));
		expectedPerform(perform,status().isOk(), asignmentInDB,null);
	}

	@Test
	public void getAsignment_WithId_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/springs/{sid}/asignments/{id}",this.sid, 1))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getAsignment_WithFeatureId_ReturnsAsignment() throws Exception {
		Asignment asignmentInDB =this.service.saveAndFlush(this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid),this.sid);
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments/features/{fid}", this.sid, asignmentInDB.getFeature().getId()));
		expectedPerform(perform,status().isOk(), asignmentInDB,null);
	}

	@Test
	public void getAsignment_WithFeatureId_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/springs/{sid}/asignments/features/{fid}", this.sid, 1))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getAsignmentsBySpringId() throws Exception {
		List<Asignment> asignmentListToSave = this.mockupAsignmentsToTest.createAsignmentsListToSaveTest(this.pid);
//		int sizeProjectToSave = projectListToSave.size();
		List<Asignment> savedAsignments = new ArrayList<>();
		for (Asignment asignmentToSave : asignmentListToSave) {
			Asignment savedAsignment =this.service.saveAndFlush(asignmentToSave,this.sid);
			savedAsignments.add(savedAsignment);
		}
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments",this.sid));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedAsignments.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedAsignments.get(i), i);
		}
	}

	@Test
	public void getAsignmentsBySpringId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments",this.sid));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postAsignmentOfSpringId_ReturnsAsignment() throws Exception {
		Asignment asignmentF1 = this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid);  //Insert Feature and User in DB
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/asignments",this.sid).content(asJsonString(asignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Asignment retrievedAsignment = this.service.getAsignmentByFeatureId(asignmentF1.getFeature().getId());
		expectedPerform(perform,status().isCreated(), retrievedAsignment,null);
	}

	@Test
	public void postAsignmentOfSpringId_WithDuplicate() throws Exception {
		Asignment asignmentF1 = this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid);
		Asignment asignmentInDB =this.service.saveAndFlush(asignmentF1,this.sid);
		Asignment asignmentDuplicateToSave = new Asignment(0,asignmentF1.getFeature(),asignmentF1.getUser());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/asignments",this.sid).content(asJsonString(asignmentDuplicateToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
		this.service.delete(asignmentInDB.getId());
	}
	
//	public void putAsignment_WithId_ReturnsAsignment() throws Exception {
//		Asignment asignmentInDB =this.service.saveAndFlush(this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid),this.sid);
//		asignmentInDB.setAvailableHours(30);
//		ResultActions perform = this.mockMvc.perform(
//				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/{id}",this.sid,asignmentInDB.getId()).content(asJsonString(asignmentInDB))
//				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
//		Asignment retrievedAsignment = this.service.getAsignmentById(asignmentInDB.getId());
//		expectedPerform(perform,status().isOk(), asignmentInDB,null);
//		assertObjectsEqual(asignmentInDB, retrievedAsignment);
//	}

	@Test
	public void putAsignment_WithId_ChangeAllFields_ReturnsAsignment() throws Exception {
		Asignment asignmentInDB =this.service.saveAndFlush(this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid),this.sid);
		Asignment updatedAsignmentF1 = this.mockupAsignmentsToTest.createAsignmentF2(asignmentInDB.getId(),this.pid);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/{id}",this.sid,asignmentInDB.getId()).content(asJsonString(updatedAsignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Asignment retrievedAsignment = this.service.getAsignmentById(updatedAsignmentF1.getId());
		expectedPerform(perform,status().isOk(), updatedAsignmentF1,null);
		assertObjectsEqual(updatedAsignmentF1, retrievedAsignment);
	}
	
	@Test
	public void putAsignment_WithId_NotFound() throws Exception {
		Asignment asignmentF1 = this.mockupAsignmentsToTest.createAsignmentF1(this.pid);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{pid}/asignments/{id}",1,1).content(asJsonString(asignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	public void putAsignmentOfSpringId_WithId_Duplicated() throws Exception {
		Asignment asignmentF1InDB =this.service.save(this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid),this.sid);
		Asignment asignmentF2InDB =this.service.save(this.mockupAsignmentsToTest.createAsignmentF2(0,this.pid),this.sid);
		asignmentF1InDB.setFeature(asignmentF2InDB.getFeature());
//		asignmentF1InDB.setAvailableHours(asignmentF2InDB.getAvailableHours());		
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/{id}", this.sid, asignmentF1InDB.getId()).content(asJsonString(asignmentF1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void deleteAsignment_WithId() throws Exception {
		Asignment asignmentInDB =this.service.save(this.mockupAsignmentsToTest.createAsignmentF1(0,this.pid),this.sid);
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/asignments/{id}", this.sid, asignmentInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Asignment> asignments = this.service.getAsignmentsBySpringId(this.sid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(asignments));
	}

	@Test
	public void deleteAsingments_WithSpringId() throws Exception {
		List<Asignment> asignmentListToSave = this.mockupAsignmentsToTest.createAsignmentsListToSaveTest(this.pid);
		List<Asignment> savedAsignments = new ArrayList<>();
		for (Asignment asignmentToSave : asignmentListToSave) {
			Asignment savedAsignment =this.service.saveAndFlush(asignmentToSave,this.sid);
			savedAsignments.add(savedAsignment);
		}
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/asignments", this.sid));
		perform	.andExpect(status().isNoContent());
		Iterable<Asignment> projects = this.service.getAsignmentsBySpringId(this.sid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Asignment asignment, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id").value(asignment.getId()))
				.andExpect(jsonPath(arr + "feature.code").value(asignment.getFeature().getCode()))
				.andExpect(jsonPath(arr + "feature.title").value(asignment.getFeature().getTitle()))
				.andExpect(jsonPath(arr + "user.username").value(asignment.getUser().getUsername()))
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
