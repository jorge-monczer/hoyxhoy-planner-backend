package com.hhplanner.entities.integration;

import static com.hhplanner.utils.AssertUtils.assertObjectsEqual;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.service.AsignmentService;
import com.hhplanner.mockups.AsignmentBuilder;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.SpendingBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class AsignmentIntegrationTest {
	private static int ANY_SPRING_ID = 1233333;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AsignmentService service;

	@Autowired
	private BuilderFactory builderFactory;

	private AsignmentBuilder builder;

    @Before
    public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getAsignmentBuilder();
    }
    
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getAsignment_WithId_ReturnsAsignment() throws Exception {
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments/{id}",this.builder.getSpringId(), asignmentInDB.getId()));
		expectedPerform(perform,status().isOk(), asignmentInDB,null);
	}

	@Test
	public void getAsignment_WithId_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments/{id}",ANY_SPRING_ID, 1))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_ASIGNED));
	}
	
	@Test
	public void getAsignment_WithFeatureId_ReturnsAsignment() throws Exception {
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments/features/{fid}", this.builder.getSpringId(), asignmentInDB.getFeature().getId()));
		expectedPerform(perform,status().isOk(), asignmentInDB,null);
	}

	@Test
	public void getAsignment_WithId_ReturnsAsignmentWithSpendings() throws Exception {
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		Float[] arr = {null,8.0f,null,null,null,6.0f,null,7.0f};
		asignmentInDB.setSpendingsInt(Arrays.asList(arr));
		SpendingBuilder spendingBuilder = this.builderFactory.getSpendingBuilder();
		spendingBuilder.buildSpendingsAndSave(arr);
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments/{id}",this.builder.getSpringId(), asignmentInDB.getId()));
		expectedPerform(perform,status().isOk(), asignmentInDB,null);
		spendingBuilder.deleteAll();
	}

	@Test
	public void getAsignment_WithFeatureId_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments/features/{fid}", ANY_SPRING_ID, 1))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_ASIGNED));
	}
	
	@Test
	public void getAsignmentsBySpringId() throws Exception {
		List<Asignment> savedAsignments = this.createAsignmentListSavedTest();
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments",this.builder.getSpringId()));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedAsignments.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedAsignments.get(i), i);
		}
	}
	
	private List<Asignment> createAsignmentListSavedTest() {
		List<Asignment> savedList = new ArrayList<>();
		savedList.add(this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment());
		savedList.add(this.builder.buildF2().buildU2().buildCapacity(6).buildAsignment(0).save().getAsignment());
		savedList.add(this.builder.buildF3().buildU3().buildCapacity(4).buildAsignment(0).save().getAsignment());
		return savedList;
	}
	
	@Test
	public void getAsignmentsBySpringId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/springs/{sid}/asignments",ANY_SPRING_ID));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postAsignmentOfSpringId_ReturnsAsignment() throws Exception {
		Asignment asignmentF1 = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).getAsignment();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/asignments",this.builder.getSpringId()).content(asJsonString(asignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Asignment retrievedAsignment = this.service.getAsignmentByFeatureId(asignmentF1.getFeature().getId());
		expectedPerform(perform,status().isCreated(), retrievedAsignment,null);
	}

	@Test
	public void postAsignmentOfSpringId_WithDuplicate() throws Exception {
		this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		Asignment asignmentDuplicateToSave = this.builder.buildAsignment(0).getAsignment();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/asignments",this.builder.getSpringId()).content(asJsonString(asignmentDuplicateToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_ALREADY_ASIGNED));
	}

	@Test
	public void postAsignmentOfSpringId_ExcededAvailableUser() throws Exception {
		// Spring 1  10 days, F1 40 hours, F2 52 hours User 1 capacity  8 // 8*10=80 < 40+52= 92
		this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save();
		Asignment asignmentF2ExcededToSave = this.builder.buildF2().buildAsignment(0).getAsignment();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/asignments",this.builder.getSpringId()).content(asJsonString(asignmentF2ExcededToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.USER_CAPACITY_INSUFFICIENT));
		assertFalse(this.service.existsAsignmentByFeatureCode(asignmentF2ExcededToSave.getFeature().getCode()));
	}

	@Test
	public void postAsignmentOfSpringId_ReturnsCapacityNotFound() throws Exception {
		Asignment asignmentF1 = this.builder.buildP1().buildS1().buildF1().buildU1().buildAsignment(0).getAsignment();  //No Capacity in DB
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/springs/{sid}/asignments",this.builder.getSpringId()).content(asJsonString(asignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.CAPACITY_NOT_FOUND));
	}

	@Test
	public void putAsignment_WithId_ChangeAllFields_ReturnsAsignment() throws Exception {
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		Asignment updatedAsignmentF1 = this.builder.buildF2().buildU2().buildCapacity(6).buildAsignment(asignmentInDB.getId()).getAsignment();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/{id}",this.builder.getSpringId(),asignmentInDB.getId()).content(asJsonString(updatedAsignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Asignment retrievedAsignment = this.service.getAsignmentById(updatedAsignmentF1.getId());
		expectedPerform(perform,status().isOk(), updatedAsignmentF1,null);
		assertObjectsEqual(updatedAsignmentF1, retrievedAsignment);
	}

	@Test
	public void putAsignment_WithId_ChangeFeature_ExcededAvailableUser() throws Exception {
		this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		Asignment asignmentF2ExcededToSave = this.builder.buildF2().buildAsignment(0).getAsignment();
		Asignment asignmentInDB = this.builder.buildF3().buildU3().buildCapacity(8).buildAsignment(0).save().getAsignment();
		asignmentF2ExcededToSave.setId(asignmentInDB.getId());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/{id}",this.builder.getSpringId(),asignmentF2ExcededToSave.getId()).content(asJsonString(asignmentF2ExcededToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.USER_CAPACITY_INSUFFICIENT));
		Asignment retrievedAsignment = this.service.getAsignmentById(asignmentInDB.getId());
		assertObjectsEqual(asignmentInDB, retrievedAsignment);
	}
	
	@Test
	public void putAsignment_WithId_ChangeAllFields_ReturnsCapacityNotFound() throws Exception {
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		Asignment updatedAsignmentF1 = this.builder.buildF2().buildU2().buildAsignment(asignmentInDB.getId()).getAsignment();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/{id}",this.builder.getSpringId(),asignmentInDB.getId()).content(asJsonString(updatedAsignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.CAPACITY_NOT_FOUND));
	}
	
	@Test
	public void putAsignment_WithId_NotFound() throws Exception {
		Asignment asignmentF1 = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(1).getAsignment(); 
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{pid}/asignments/{id}",this.builder.getSpringId(),asignmentF1.getId()).content(asJsonString(asignmentF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_ASIGNED));
	}

	@Test
	public void putAsignmentOfSpringId_WithId_Duplicated() throws Exception {
		Asignment asignmentF1InDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		Asignment asignmentF2InDB = this.builder.buildF2().buildU2().buildCapacity(6).buildAsignment(0).save().getAsignment();
		asignmentF1InDB.setFeature(asignmentF2InDB.getFeature());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/{id}", this.builder.getSpringId(), asignmentF1InDB.getId()).content(asJsonString(asignmentF1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_ALREADY_ASIGNED));
	}

	@Test
	public void putAsignment_ChangeSpendings_ReturnsAsignment() throws Exception {
		List<Asignment> asignments = new ArrayList<Asignment>();
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		SpendingBuilder spendingBuilder = this.builderFactory.getSpendingBuilder();
		spendingBuilder.buildSpendingsAndSave(new Float[] {null,8.0f,null,null,null,6.0f,null,7.0f});
		asignmentInDB.setSpendingsInt(Arrays.asList(new Float[] {7.0f,null,7.0f,null,null,7.0f,7.0f,7.0f,null,7.0f}));
		asignments.add(asignmentInDB);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/spendings",this.builder.getSpringId()).content(asJsonString(asignments))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < asignments.size(); i++) {
    		Asignment retrievedAsignment = this.service.getAsignmentById(asignments.get(i).getId());
        	expectedPerform(perform,status().isOk(), asignments.get(i), i);
    		assertObjectsEqual(asignments.get(i), retrievedAsignment);
		}
		spendingBuilder.deleteAll();
	}

	@Test
	public void putAsignment_DeleteAllSpendings_ReturnsAsignment() throws Exception {
		List<Asignment> asignments = new ArrayList<Asignment>();
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		SpendingBuilder spendingBuilder = this.builderFactory.getSpendingBuilder();
		spendingBuilder.buildSpendingsAndSave(new Float[] {8.0f,8.0f,8.0f,8.0f});
		asignmentInDB.setSpendingsInt(Arrays.asList(new Float[] {}));
		asignments.add(asignmentInDB);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/spendings",this.builder.getSpringId()).content(asJsonString(asignments))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < asignments.size(); i++) {
    		Asignment retrievedAsignment = this.service.getAsignmentById(asignments.get(i).getId());
        	expectedPerform(perform,status().isOk(), asignments.get(i), i);
    		assertObjectsEqual(asignments.get(i), retrievedAsignment);
		}
		spendingBuilder.deleteAll();
	}
	
	@Test
	public void putAllAsignments_ChangeSpendings_ReturnsAllAsignments() throws Exception {
		SpendingBuilder spendingBuilder = this.builderFactory.getSpendingBuilder();
		List<Asignment> savedAsignments = this.createAsignmentListWithSpendingsSavedTest(spendingBuilder);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/springs/{sid}/asignments/spendings",this.builder.getSpringId()).content(asJsonString(savedAsignments))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedAsignments.size(); i++) {
    		Asignment retrievedAsignment = this.service.getAsignmentById(savedAsignments.get(i).getId());
        	expectedPerform(perform,status().isOk(), savedAsignments.get(i), i);
    		assertObjectsEqual(savedAsignments.get(i), retrievedAsignment);
		}
		spendingBuilder.deleteAll();
	}

	private List<Asignment> createAsignmentListWithSpendingsSavedTest(SpendingBuilder spendingBuilder) {
		List<Asignment> savedList = new ArrayList<>();
		savedList.add(this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment());
		spendingBuilder.buildSpendingsAndSave(new Float[] {null,8.0f,null,null,null,6.0f,null,7.0f});
		this.builder.getAsignment().setSpendingsInt(Arrays.asList(new Float[] {7.0f,null,7.0f,null,null,7.0f,7.0f,7.0f,null,7.0f}));
		savedList.add(this.builder.buildF2().buildU2().buildCapacity(6).buildAsignment(0).save().getAsignment());
		spendingBuilder.buildSpendingsAndSave(new Float[] {8.0f,null,null,7.0f,null,6.0f});
		this.builder.getAsignment().setSpendingsInt(Arrays.asList(new Float[] {7.0f,7.0f,7.0f,null,null,8.0f,8.0f,8.0f,null,7.0f}));
		savedList.add(this.builder.buildF3().buildU3().buildCapacity(4).buildAsignment(0).save().getAsignment());
		spendingBuilder.buildSpendingsAndSave(new Float[] {8.0f,8.0f,7.0f,6.0f});
		this.builder.getAsignment().setSpendingsInt(Arrays.asList(new Float[] {8.0f,8.0f,7.0f,6.0f,null,null,8.0f,8.0f,8.0f,null,7.0f}));
		return savedList;
	}
	
	
	@Test
	public void deleteAsignment_WithId() throws Exception {
		Asignment asignmentInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment(0).save().getAsignment();
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/asignments/{id}", this.builder.getSpringId(), asignmentInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Asignment> asignments = this.service.getAsignmentsBySpringId(this.builder.getSpringId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(asignments));
	}

	@Test
	public void deleteAsingments_WithSpringId() throws Exception {
		this.createAsignmentListSavedTest();
		ResultActions perform = this.mockMvc.perform(delete("/api/springs/{sid}/asignments", this.builder.getSpringId()));
		perform	.andExpect(status().isNoContent());
		Iterable<Asignment> projects = this.service.getAsignmentsBySpringId(this.builder.getSpringId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Asignment asignment, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id").value(asignment.getId()))
				.andExpect(jsonPath(arr + "feature.code").value(asignment.getFeature().getCode()))
				.andExpect(jsonPath(arr + "feature.title").value(asignment.getFeature().getTitle()))
				.andExpect(jsonPath(arr + "user.username").value(asignment.getUser().getUsername()))
				.andExpect(jsonPath(arr + "spendingsInt", hasToString(asignment.getSpendingsInt().toString().replace(" ", ""))))
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
