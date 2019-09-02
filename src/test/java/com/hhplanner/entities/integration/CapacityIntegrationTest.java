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
import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.CapacityService;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.entities.service.SpringService;
import com.hhplanner.mockups.MockupCapacitiesToTest;
import com.hhplanner.mockups.MockupProjectsToTest;
import com.hhplanner.mockups.MockupSpringsToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class CapacityIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CapacityService service;

	@Autowired
	private ProjectService projectService;
	@Autowired
	private SpringService springService;
	
	@Autowired
	MockupCapacitiesToTest mockupCapacitiesToTest;

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
		this.mockupCapacitiesToTest.deleteAll();
		this.springService.delete(this.sid);
		this.projectService.delete(this.pid);
	}
	
	@Test
	public void getCapacity_WithId_ReturnsCapacity() throws Exception {
		Capacity capacityInDB = this.service.saveAndFlush(this.mockupCapacitiesToTest.createCapacityUser1(0),this.sid);
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/capacities/{id}",this.sid, capacityInDB.getId()));
		expectedPerform(perform,status().isOk(), capacityInDB,null);
	}

	@Test
	public void getCapacity_WithId_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/springs/{sid}/capacities/{id}",this.sid, 1))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getCapacitiesBySpringId() throws Exception {
		List<Capacity> capacityListToSave = this.mockupCapacitiesToTest.createCapacitiesListToSaveTest();
//		int sizeProjectToSave = projectListToSave.size();
		List<Capacity> savedCapacitys = new ArrayList<>();
		for (Capacity capacityToSave : capacityListToSave) {
			Capacity savedCapacity =this.service.saveAndFlush(capacityToSave,this.sid);
			savedCapacitys.add(savedCapacity);
		}
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/capacities",this.sid));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedCapacitys.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedCapacitys.get(i), i);
		}
	}

	@Test
	public void getCapacitysBySpringId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/capacities",this.sid));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postCapacityOfSpringId_ReturnsCapacity() throws Exception {
		Capacity capacityUser1 = this.mockupCapacitiesToTest.createCapacityUser1(0); 
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/capacities",this.sid).content(asJsonString(capacityUser1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Capacity retrievedCapacity = this.service.getCapacityByUserAndSpringId(capacityUser1.getUser(), this.sid);
		expectedPerform(perform,status().isCreated(), retrievedCapacity,null);
	}

	@Test
	public void postCapacityOfSpringId_WithDuplicate() throws Exception {
		Capacity capacityUser1 = this.mockupCapacitiesToTest.createCapacityUser1(0);
		Capacity capacityInDB =this.service.saveAndFlush(capacityUser1,this.sid);
		Capacity capacityDuplicateToSave = new Capacity(0,capacityUser1.getUser(),8);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/capacities",this.sid).content(asJsonString(capacityDuplicateToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
		this.service.delete(capacityInDB.getId());
	}
	
	@Test
	public void putCapacity_WithId_ReturnsCapacity() throws Exception {
		Capacity capacityInDB =this.service.saveAndFlush(this.mockupCapacitiesToTest.createCapacityUser1(0),this.sid);
		capacityInDB.setAvailableHours(4);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/capacities/{id}",this.sid,capacityInDB.getId()).content(asJsonString(capacityInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Capacity retrievedCapacity = this.service.getCapacityById(capacityInDB.getId());
		expectedPerform(perform,status().isOk(), capacityInDB,null);
		assertObjectsEqual(capacityInDB, retrievedCapacity);
	}

	@Test
	public void putCapacity_WithId_ChangeAllFields_ReturnsCapacity() throws Exception {
		Capacity capacityInDB =this.service.saveAndFlush(this.mockupCapacitiesToTest.createCapacityUser1(0),this.sid);
		Capacity updatedCapacityUser1 = this.mockupCapacitiesToTest.createCapacityUser2(capacityInDB.getId());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/capacities/{id}",this.sid,capacityInDB.getId()).content(asJsonString(updatedCapacityUser1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Capacity retrievedCapacity = this.service.getCapacityById(updatedCapacityUser1.getId());
		expectedPerform(perform,status().isOk(), updatedCapacityUser1,null);
		assertObjectsEqual(updatedCapacityUser1, retrievedCapacity);
	}
	
	@Test
	public void putCapacity_WithId_NotFound() throws Exception {
		Capacity capacityUser1 = this.mockupCapacitiesToTest.createCapacityUser1();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{pid}/capacities/{id}",1,1).content(asJsonString(capacityUser1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	public void putCapacityOfSpringId_WithId_Duplicated() throws Exception {
		Capacity capacityUser1InDB =this.service.saveAndFlush(this.mockupCapacitiesToTest.createCapacityUser1(0),this.sid);
		Capacity capacityUser2InDB =this.service.saveAndFlush(this.mockupCapacitiesToTest.createCapacityUser2(0),this.sid);
		capacityUser1InDB.setUser(capacityUser2InDB.getUser());	
		capacityUser1InDB.setAvailableHours(5);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/capacities/{id}", this.sid, capacityUser1InDB.getId()).content(asJsonString(capacityUser1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void deleteCapacity_WithId() throws Exception {
		Capacity capacityInDB =this.service.save(this.mockupCapacitiesToTest.createCapacityUser1(0),this.sid);
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/capacities/{id}", this.sid, capacityInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Capacity> capacities = this.service.getCapacitysBySpringId(this.sid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(capacities));
	}

	@Test
	public void deleteAsingments_WithSpringId() throws Exception {
		List<Capacity> capacityListToSave = this.mockupCapacitiesToTest.createCapacitiesListToSaveTest();
		List<Capacity> savedCapacitys = new ArrayList<>();
		for (Capacity capacityToSave : capacityListToSave) {
			Capacity savedCapacity =this.service.saveAndFlush(capacityToSave,this.sid);
			savedCapacitys.add(savedCapacity);
		}
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/capacities", this.sid));
		perform	.andExpect(status().isNoContent());
		Iterable<Capacity> projects = this.service.getCapacitysBySpringId(this.sid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Capacity capacity, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id").value(capacity.getId()))
				.andExpect(jsonPath(arr + "user.username").value(capacity.getUser().getUsername()))
				.andExpect(jsonPath(arr + "availableHours").value(capacity.getAvailableHours()))
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
