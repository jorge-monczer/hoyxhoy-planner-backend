package com.hhplanner.entities.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplanner.HoyxhoyPlannerBackendApplication;
import com.hhplanner.entities.model.SimpleIdCodeName;
import com.hhplanner.entities.model.Spending;
import com.hhplanner.entities.model.Spring;
import com.hhplanner.entities.service.OutlookService;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.SpendingBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@AutoConfigureMockMvc
public class OutlookIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OutlookService service;

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
	public void getQ_SpringsByProjectId() throws Exception {
		List<Spending> savedSpendings = this.createSpendingListSavedTest();
		Spring spring = this.builderFactory.getSpringBuilder().getSpring();
		ResultActions perform = this.mockMvc.perform(get("/api/projects/{pid}/q_spring",spring.getProject().getId()));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
       	expectedQ_SpringPerform(perform,status().isOk(), spring.getProject(), 0, 40.5,null,"Total hours estimated for ");
       	expectedQ_SpringPerform(perform,status().isOk(), spring, 1, 24.0,0.5926,"Hours consumed in ");
	}
	
	
	private List<Spending> createSpendingListSavedTest() {
		List<Spending> savedList = new ArrayList<>();
		savedList.add(this.builder.buildP1().buildS1().buildF1().buildU1().buildCapacity(8).buildAsignment().buildSpending(1,8).save().getSpending());
		savedList.add(this.builder.buildSpending(2,7).save().getSpending());
		savedList.add(this.builder.buildSpending(3,9).save().getSpending());
		return savedList;
	}
	
	private static void expectedQ_SpringPerform(ResultActions perform, ResultMatcher status, SimpleIdCodeName spring, Integer idx, Double spent, Double advance, String description) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "name").value(description + spring.getCode() + "-" + spring.getName()))
				.andExpect(jsonPath(arr + "spent").value(spent))
				.andExpect(jsonPath(arr + "advance").value(advance))
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
