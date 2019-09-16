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
import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.service.CapacityService;
import com.hhplanner.mockups.AsignmentBuilder;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.CapacityBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class CapacityIntegrationTest {
	private static int ANY_SPRING_ID = 1233333;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CapacityService service;

	@Autowired
	private BuilderFactory builderFactory;

	private CapacityBuilder builder;

	@Before
	public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getCapacityBuilder();
	}
	
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getCapacity_WithId_ReturnsCapacity() throws Exception {
		Capacity capacityInDB = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity();
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/capacities/{id}",this.builder.getSpringId(), capacityInDB.getId()));
		expectedPerform(perform,status().isOk(), capacityInDB,null);
	}

	@Test
	public void getCapacity_WithId_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/capacities/{id}",ANY_SPRING_ID, 1))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.CAPACITY_NOT_FOUND));
	}
	
	@Test
	public void getCapacitiesBySpringId() throws Exception {
		List<Capacity> savedCapacitys = this.createCapacityListSavedTest();
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/capacities",this.builder.getSpringId()));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedCapacitys.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedCapacitys.get(i), i);
		}
	}

	private List<Capacity> createCapacityListSavedTest() {
		List<Capacity> savedList = new ArrayList<>();
		savedList.add(this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity());
		savedList.add(this.builder.buildU2().buildCapacity(0, 6).save().getCapacity());
		savedList.add(this.builder.buildU3().buildCapacity(0, 4).save().getCapacity());
		return savedList;
	}
	
	@Test
	public void getCapacitiesBySpringId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/capacities",ANY_SPRING_ID));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postCapacityOfSpringId_ReturnsCapacity() throws Exception {
		Capacity capacityUser1 = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).getCapacity(); 
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/capacities",this.builder.getSpringId()).content(asJsonString(capacityUser1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Capacity retrievedCapacity = this.service.getCapacityByUserAndSpringId(capacityUser1.getUser(), this.builder.getSpringId());
		expectedPerform(perform,status().isCreated(), retrievedCapacity,null);
	}

	@Test
	public void postCapacityOfSpringId_WithDuplicate() throws Exception {
		this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity();
		Capacity capacityDuplicateToSave = this.builder.buildCapacity(0, 8).getCapacity();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/capacities",this.builder.getSpringId()).content(asJsonString(capacityDuplicateToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.CAPACITY_DUPLICATED));
	}
	
	@Test
	public void putCapacity_WithId_ReturnsCapacity() throws Exception {
		Capacity capacityInDB = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity();
		capacityInDB.setAvailableHours(4);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/capacities/{id}",this.builder.getSpringId(),capacityInDB.getId()).content(asJsonString(capacityInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Capacity retrievedCapacity = this.service.getCapacityById(capacityInDB.getId());
		expectedPerform(perform,status().isOk(), capacityInDB,null);
		assertObjectsEqual(capacityInDB, retrievedCapacity);
	}

	@Test
	public void putCapacity_WithId_ChangeAllFields_ReturnsCapacity() throws Exception {
		Capacity capacityInDB = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity();
		Capacity updatedCapacityUser1 = this.builder.buildU2().buildCapacity(capacityInDB.getId(), 6).getCapacity();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/capacities/{id}",this.builder.getSpringId(),capacityInDB.getId()).content(asJsonString(updatedCapacityUser1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Capacity retrievedCapacity = this.service.getCapacityById(updatedCapacityUser1.getId());
		expectedPerform(perform,status().isOk(), updatedCapacityUser1,null);
		assertObjectsEqual(updatedCapacityUser1, retrievedCapacity);
	}
	
	@Test
	public void putCapacity_WithId_NotFound() throws Exception {
		Capacity capacityUser1 = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).getCapacity();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{pid}/capacities/{id}",ANY_SPRING_ID,1).content(asJsonString(capacityUser1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.CAPACITY_NOT_FOUND));
	}

	@Test
	public void putCapacityOfSpringId_WithId_Duplicated() throws Exception {
		Capacity capacityUser1InDB = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity();
		Capacity capacityUser2InDB = this.builder.buildU2().buildCapacity(0, 6).save().getCapacity();
		capacityUser1InDB.setUser(capacityUser2InDB.getUser());	
		capacityUser1InDB.setAvailableHours(5);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/capacities/{id}", this.builder.getSpringId(), capacityUser1InDB.getId()).content(asJsonString(capacityUser1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.CAPACITY_DUPLICATED));
	}

	@Test
	public void putCapacityOfSpringId_WithId_AvailableHoursInsufficient() throws Exception {
		Capacity capacityInDB = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity();
		AsignmentBuilder asbuilder = this.builderFactory.getAsignmentBuilder();
		asbuilder.buildF1().buildAsignment(0).save().getAsignment();
		capacityInDB.setAvailableHours(3);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/capacities/{id}",this.builder.getSpringId(),capacityInDB.getId()).content(asJsonString(capacityInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.USER_CAPACITY_INSUFFICIENT));
		Capacity retrievedCapacity = this.service.getCapacityById(capacityInDB.getId());
		assertObjectsEqual(this.builder.buildCapacity(capacityInDB.getId(),8).getCapacity(), retrievedCapacity);
		asbuilder.deleteAll();
	}
	
	@Test
	public void deleteCapacity_WithId() throws Exception {
		Capacity capacityInDB = this.builder.buildP1().buildS1().buildU1().buildCapacity(0, 8).save().getCapacity();
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/capacities/{id}", this.builder.getSpringId(), capacityInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Capacity> capacities = this.service.getCapacitysBySpringId(this.builder.getSpringId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(capacities));
	}

	@Test
	public void deleteAsingments_WithSpringId() throws Exception {
		this.createCapacityListSavedTest();
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/capacities", this.builder.getSpringId()));
		perform	.andExpect(status().isNoContent());
		Iterable<Capacity> projects = this.service.getCapacitysBySpringId(this.builder.getSpringId());
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
