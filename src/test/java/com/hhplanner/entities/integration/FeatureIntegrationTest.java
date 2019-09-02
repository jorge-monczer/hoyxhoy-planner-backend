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
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.Project;
import com.hhplanner.entities.service.FeatureService;
import com.hhplanner.entities.service.ProjectService;
import com.hhplanner.mockups.MockupFeaturesToTest;
import com.hhplanner.mockups.MockupProjectsToTest;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class FeatureIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FeatureService service;

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
	public void getFeature_WithId_ReturnsFeature() throws Exception {
		Feature featureInDB = this.service.save(MockupFeaturesToTest.createFeatureF1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features/{id}",this.pid, featureInDB.getId()));
		expectedPerform(perform,status().isOk(), featureInDB,null);
	}

	@Test
	public void getFeature_WithId_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/{pid}/features/{id}",this.pid, 1))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getFeature_WithCode_ReturnsFeature() throws Exception {
		Feature featureInDB =this.service.saveAndFlush(MockupFeaturesToTest.createFeatureF1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features/code/{code}", this.pid, featureInDB.getCode()));
		expectedPerform(perform,status().isOk(), featureInDB,null);
	}

	@Test
	public void getFeature_WithCode_NotFound_Returns404() throws Exception {
		this.mockMvc.perform(get("/api/projects/{pid}/features/code/{code}", this.pid, "F1"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getFeaturesByProjectId() throws Exception {
		List<Feature> featureListToSave = MockupFeaturesToTest.createFeaturesListToSaveTest();
//		int sizeProjectToSave = projectListToSave.size();
		List<Feature> savedFeatures = new ArrayList<>();
		for (Feature featureToSave : featureListToSave) {
			Feature savedFeature =this.service.saveAndFlush(featureToSave,this.pid);
			savedFeatures.add(savedFeature);
		}
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features",this.pid));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedFeatures.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedFeatures.get(i), i);
		}
	}

	@Test
	public void getFeaturesByProjectId_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/features",this.pid));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postFeatureOfProjectId_ReturnsFeature() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/features",this.pid).content(asJsonString(MockupFeaturesToTest.createFeatureF1(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Feature retrievedProject = this.service.getFeatureByCodeAndProjectId("F1",this.pid);
		expectedPerform(perform,status().isCreated(), retrievedProject,null);
	}

	@Test
	public void postFeatureOfProjectId_WithDuplicateCode() throws Exception {
		Feature projectInDB =this.service.saveAndFlush(MockupFeaturesToTest.createFeatureF1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects/{pid}/features",this.pid).content(asJsonString(MockupFeaturesToTest.createFeatureF1(0)))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
		this.service.delete(projectInDB.getId());
	}
	
	@Test
	public void putFeature_WithId_ReturnsFeature() throws Exception {
		Feature featureInDB =this.service.saveAndFlush(MockupFeaturesToTest.createFeatureF1(0),this.pid);
		featureInDB.setEstimatedHours(30);
		featureInDB.setCommittedDate(LocalDate.of(2019, 11, 30));
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}",this.pid,featureInDB.getId()).content(asJsonString(featureInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Feature retrievedFeature = this.service.getFeatureById(featureInDB.getId());
		expectedPerform(perform,status().isOk(), featureInDB,null);
		assertObjectsEqual(featureInDB, retrievedFeature);
	}

	@Test
	public void putFeature_WithId_ChangeAllFields_ReturnsFeature() throws Exception {
		Feature featureInDB =this.service.saveAndFlush(MockupFeaturesToTest.createFeatureF1(0),this.pid);
		Feature updatedFeatureF1 = MockupFeaturesToTest.createFeatureF2(featureInDB.getId());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}",this.pid,featureInDB.getId()).content(asJsonString(updatedFeatureF1))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Feature retrievedFeature = this.service.getFeatureById(updatedFeatureF1.getId());
		expectedPerform(perform,status().isOk(), updatedFeatureF1,null);
		assertObjectsEqual(updatedFeatureF1, retrievedFeature);
	}
	
	@Test
	public void putFeature_WithId_NotFound() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}",1,1).content(asJsonString(MockupFeaturesToTest.createFeatureF1()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("$").doesNotExist());
	}

	@Test
	public void putFeatureOfProjectId_WithId_DuplicatedCode() throws Exception {
		Feature featureF1InDB =this.service.save(MockupFeaturesToTest.createFeatureF1(0),this.pid);
		Feature featureF2InDB =this.service.save(MockupFeaturesToTest.createFeatureF2(0),this.pid);
		featureF1InDB.setCode(featureF2InDB.getCode());
		featureF1InDB.setCommittedDate(featureF2InDB.getCommittedDate());		
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/projects/{pid}/features/{id}", this.pid, featureF1InDB.getId()).content(asJsonString(featureF1InDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isForbidden());
		perform.andExpect(jsonPath("$").doesNotExist());
	}
	
	@Test
	public void deleteProject_WithId() throws Exception {
		Feature featureInDB =this.service.save(MockupFeaturesToTest.createFeatureF1(0),this.pid);
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/features/{id}", this.pid, featureInDB.getId()));
		perform.andExpect(status().isOk());
		Iterable<Feature> features = this.service.getFeaturesByProjectId(this.pid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(features));
	}

	@Test
	public void deleteProjects() throws Exception {
		List<Feature> featureListToSave = MockupFeaturesToTest.createFeaturesListToSaveTest();
		List<Feature> savedFeatures = new ArrayList<>();
		for (Feature featureToSave : featureListToSave) {
			Feature savedFeature =this.service.save(featureToSave,this.pid);
			savedFeatures.add(savedFeature);
		}
		ResultActions perform = this.mockMvc.perform(delete("/api/projects/{pid}/features", this.pid));
		perform	.andExpect(status().isNoContent());
		Iterable<Feature> projects = this.service.getFeaturesByProjectId(this.pid);
		Assert.assertTrue(IterableUtil.isNullOrEmpty(projects));
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
