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
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.service.FeatureService;
import com.hhplanner.mockups.AsignmentBuilder;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.FeatureBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class FeatureIntegrationTest {
	private static int ANY_PROJEC_ID = 1233333;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FeatureService service;

	@Autowired
	private BuilderFactory builderFactory;

	private FeatureBuilder builder;

	@Before
	public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getFeatureBuilder();
	}
	
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getFeature_WithId_ReturnsFeature() throws Exception {
		Feature featureInDB = this.builder.buildP1().buildF1(0).save().getFeature();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features/{id}",this.builder.getProjectId(), featureInDB.getId()));
		expectedPerform(perform,status().isOk(), featureInDB,null);
	}

	@Test
	public void getFeature_WithId_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features/{id}",ANY_PROJEC_ID, 1))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_FOUND));
	}
	
	@Test
	public void getFeature_WithCode_ReturnsFeature() throws Exception {
		Feature featureInDB = this.builder.buildP1().buildF1(0).save().getFeature();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features/code/{code}", this.builder.getProjectId(), featureInDB.getCode()));
		expectedPerform(perform,status().isOk(), featureInDB,null);
	}

	@Test
	public void getFeature_WithCode_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features/code/{code}", ANY_PROJEC_ID, "F1"))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_FOUND));
	}
	
	@Test
	public void getFeaturesByProjectId() throws Exception {
		List<Feature> savedFeatures = this.createFeatureListSavedTest();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features",this.builder.getProjectId()));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedFeatures.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedFeatures.get(i), i);
		}
	}

	private List<Feature> createFeatureListSavedTest() {
		List<Feature> savedList = new ArrayList<>();
		savedList.add(this.builder.buildP1().buildF1(0).save().getFeature());
		savedList.add(this.builder.buildF2(0).save().getFeature());
		savedList.add(this.builder.buildF3(0).save().getFeature());
		return savedList;
	}
	
	@Test
	public void getFeaturesByProjectId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features",ANY_PROJEC_ID));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postFeatureOfProjectId_ReturnsFeature() throws Exception {
		Feature featureF1 = this.builder.buildP1().buildF1(0).getFeature();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/features",this.builder.getProjectId()).content(asJsonString(featureF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Feature retrievedFeature = this.service.getFeatureByCodeAndProjectId(featureF1.getCode(),this.builder.getProjectId());
		expectedPerform(perform,status().isCreated(), retrievedFeature,null);
	}

	@Test
	public void postFeatureOfProjectId_WithDuplicateCode() throws Exception {
		this.builder.buildP1().buildF1(0).save().getFeature();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/features",this.builder.getProjectId()).content(asJsonString(this.builder.buildF1(0).getFeature()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_DUPLICATED));
	}
	
	@Test
	public void putFeature_WithId_ReturnsFeature() throws Exception {
		Feature featureInDB = this.builder.buildP1().buildF1(0).save().getFeature();
		featureInDB.setEstimatedHours(30);
		featureInDB.setCommittedDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}",this.builder.getProjectId(),featureInDB.getId()).content(asJsonString(featureInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Feature retrievedFeature = this.service.getFeatureById(featureInDB.getId());
		expectedPerform(perform,status().isOk(), featureInDB,null);
		assertObjectsEqual(featureInDB, retrievedFeature);
	}

	@Test
	public void putFeature_WithId_ChangeAllFields_ReturnsFeature() throws Exception {
		Feature featureInDB = this.builder.buildP1().buildF1(0).save().getFeature();
		Feature updatedFeatureF1 = this.builder.buildF2(featureInDB.getId()).getFeature();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}",this.builder.getProjectId(),featureInDB.getId()).content(asJsonString(updatedFeatureF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Feature retrievedFeature = this.service.getFeatureById(updatedFeatureF1.getId());
		expectedPerform(perform,status().isOk(), updatedFeatureF1,null);
		assertObjectsEqual(updatedFeatureF1, retrievedFeature);
	}
	
	@Test
	public void putFeature_WithId_NotFound() throws Exception {
		this.builder.buildP1();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}",this.builder.getProjectId(),1).content(asJsonString(this.builder.buildF1(1)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_FOUND));
	}

	@Test
	public void putFeatureOfProjectId_WithId_DuplicatedCode() throws Exception {
		Feature featureF1InDB = this.builder.buildP1().buildF1(0).save().getFeature();
		Feature featureF2InDB = this.builder.buildF2(0).save().getFeature();
		featureF1InDB.setCode(featureF2InDB.getCode());
		featureF1InDB.setCommittedDate(featureF2InDB.getCommittedDate());		
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}", this.builder.getProjectId(), featureF1InDB.getId()).content(asJsonString(featureF1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_DUPLICATED));
	}

	@Test
	public void putFeature_WithId_ExceddedAvailableHoursForAsignment() throws Exception {
		Feature featureInDB = this.builder.buildP1().buildF1(0).save().getFeature(); // create F1  40 hs.
		AsignmentBuilder asbuilder = this.builderFactory.getAsignmentBuilder();
		asbuilder.buildS1().buildU1().buildCapacity(5).buildAsignment(0).save().getAsignment(); // asign U1 x 5 hs. consume 8 days.
		featureInDB.setEstimatedHours(55);  // with 55 consume 11 days and spring has 10 days
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}",this.builder.getProjectId(),featureInDB.getId()).content(asJsonString(featureInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Feature retrievedFeature = this.service.getFeatureById(featureInDB.getId());
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.USER_CAPACITY_INSUFFICIENT));
		assertObjectsEqual(this.builder.buildF1(featureInDB.getId()).getFeature(), retrievedFeature);
		asbuilder.deleteAll();
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		Feature featureInDB = this.builder.buildP1().buildF1(0).save().getFeature();
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/features/{id}", this.builder.getProjectId(), featureInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Feature> features = this.service.getFeaturesByProjectId(this.builder.getProjectId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(features));
	}

	@Test
	public void deleteProjects() throws Exception {
		this.createFeatureListSavedTest();
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/features", this.builder.getProjectId()));
		perform	.andExpect(status().isNoContent());
		Iterable<Feature> features = this.service.getFeaturesByProjectId(this.builder.getProjectId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(features));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Feature feature, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id").value(feature.getId()))
				.andExpect(jsonPath(arr + "code").value(feature.getCode()))
				.andExpect(jsonPath(arr + "title").value(feature.getTitle()))
				.andExpect(jsonPath(arr + "committedDate").value(feature.getCommittedDate().format(DateTimeFormatter.ISO_LOCAL_DATE)))
				.andExpect(jsonPath(arr + "estimatedHours").value(feature.getEstimatedHours()))
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
