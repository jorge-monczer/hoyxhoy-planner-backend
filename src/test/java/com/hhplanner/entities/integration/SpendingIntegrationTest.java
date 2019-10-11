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
import com.hhplanner.entities.model.Spending;
import com.hhplanner.entities.service.SpendingService;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.SpendingBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class SpendingIntegrationTest {
	private static int ANY_ASIGNMENT_ID = 1233333;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SpendingService service;

	@Autowired
	private BuilderFactory builderFactory;

	private SpendingBuilder builder;

    @Before
    public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getSpendingBuilder();
    }
    
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getSpending_WithSpentAndDay_ReturnsSpending() throws Exception {
		Spending spendingInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1, 8).save().getSpending();
		ResultActions perform = this.mockMvc.perform(get("/api/asignments/{aid}/spendings/{day}",this.builder.getAsignmentId(), spendingInDB.getId().getNumDay()));
		expectedPerform(perform,status().isOk(), spendingInDB,null);
	}

	@Test
	public void getSpending_WithAsignmentIdAndDay_NotFound_Returns404() throws Exception {
		this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1, 8).getSpending();
		ResultActions perform = this.mockMvc.perform(get("/api/asignments/{aid}/spendings/{day}",this.builder.getAsignmentId(), 1))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPENDING_NOT_FOUND));
	}
	
	public void getSpendingsByAsignmentId() throws Exception {
		List<Spending> savedSpendings = this.createSpendingListSavedTest();
		ResultActions perform = this.mockMvc.perform(get("/api/asignments/{aid}/spendings",this.builder.getAsignmentId()));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedSpendings.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedSpendings.get(i), i);
		}
	}

	private List<Spending> createSpendingListSavedTest() {
		List<Spending> savedList = new ArrayList<>();
		savedList.add(this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).save().getSpending());
		savedList.add(this.builder.buildSpending(2,7).save().getSpending());
		savedList.add(this.builder.buildSpending(3,9).save().getSpending());
		return savedList;
	}
	
	@Test
	public void getSpendingsByAsignmentIdAndDay_ReturnsNoContent() throws Exception {
		this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1, 8).getSpending();
		ResultActions perform = this.mockMvc.perform(get("/api/asignments/{aid}/spendings",this.builder.getAsignmentId()));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postSpendingOfAsignmentIdAndDay_ReturnsSpending() throws Exception {
		Spending spending = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).getSpending();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/asignments/{aid}/spendings/{day}",this.builder.getAsignmentId(),1).content(asJsonString(spending))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spending retrievedSpending = this.service.getSpendingById(this.builder.getAsignmentId(), 1);
		expectedPerform(perform,status().isCreated(), retrievedSpending,null);
	}

	@Test
	public void postSpendingOfAsignmentIdAndDay_WithDuplicate() throws Exception {
		this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).save().getSpending();
		Spending spendingDuplicateToSave = this.builder.buildSpending(1,8).getSpending();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/asignments/{aid}/spendings/{day}",this.builder.getAsignmentId(),1).content(asJsonString(spendingDuplicateToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPENDING_DUPLICATED));
	}

	@Test
	public void postSpendingOfAsignmentId_ReturnsAsingmentNotFound() throws Exception {
		Spending spendingToSave = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).getSpending();  //No Capacity in DB
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/asignments/{aid}/spendings/{day}",ANY_ASIGNMENT_ID,1).content(asJsonString(spendingToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_ASIGNED));
	}

	@Test
	public void putSpending_WithAsignmentIdAndDay_ChangeAllFields_ReturnsSpending() throws Exception {
		Spending spendingInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1, 8).save().getSpending();
		Spending updatedSpending = this.builder.buildSpending(1,6).getSpending();
		this.builderFactory.getAsignmentBuilder().getAsignment().getSpendingsInt().add(6);
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/asignments/{aid}/spendings/{day}",this.builder.getAsignmentId(),spendingInDB.getId().getNumDay()).content(asJsonString(updatedSpending))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Spending retrievedSpending = this.service.getSpendingById(updatedSpending.getId().getAsignment().getId(),updatedSpending.getId().getNumDay());
		expectedPerform(perform,status().isOk(), updatedSpending,null);
		assertObjectsEqual(updatedSpending, retrievedSpending);
	}

	@Test
	public void putSpending_WithAsignmentIdAndDay_ChangeAllFields_ReturnsAsingmentNotFound() throws Exception {
		Spending spendingInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).save().getSpending();
		Spending updatedSpending = this.builder.buildSpending(1,6).getSpending();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/asignments/{aid}/spendings/{day}",ANY_ASIGNMENT_ID,spendingInDB.getId().getNumDay()).content(asJsonString(updatedSpending))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.FEATURE_NOT_ASIGNED));
	}
	
	@Test
	public void putSpending_WithAsignmentIdAndDay_NotFound() throws Exception {
		Spending spending = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).getSpending(); 
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/asignments/{aid}/spendings/{day}",this.builder.getAsignmentId(),spending.getId().getNumDay()).content(asJsonString(spending))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.SPENDING_NOT_FOUND));
	}

	
	public void deleteSpending_WithAsignmentIdAndDay() throws Exception {
		Spending spendingInDB = this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).save().getSpending();
		ResultActions perform = this.mockMvc.perform(delete("/api/asignments/{aid}/spendings/{day}", this.builder.getAsignmentId(),spendingInDB.getId().getNumDay() ));
		perform.andExpect(status().isOk());
		Iterable<Spending> spendings = this.service.getSpendingsByAsignmentId(this.builder.getAsignmentId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(spendings));
	}

	@Test
	public void deleteAsingments_WithAsignmentId() throws Exception {
		this.createSpendingListSavedTest();
		ResultActions perform = this.mockMvc.perform(delete("/api/asignments/{aid}/spendings", this.builder.getAsignmentId()));
		perform	.andExpect(status().isNoContent());
		Iterable<Spending> spendings = this.service.getSpendingsByAsignmentId(this.builder.getAsignmentId());
		Assert.assertTrue(IterableUtil.isNullOrEmpty(spendings));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Spending spending, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "id.numDay").value(spending.getId().getNumDay()))
				.andExpect(jsonPath(arr + "id.asignment.id").value(spending.getId().getAsignment().getId()))
				.andExpect(jsonPath(arr + "spent").value(spending.getSpent()))
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
