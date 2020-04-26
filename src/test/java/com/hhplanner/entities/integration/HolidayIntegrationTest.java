package com.hhplanner.entities.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
import com.hhplanner.entities.model.Holiday;
import com.hhplanner.entities.service.HolidayService;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.HolidayBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
@Transactional
@AutoConfigureMockMvc
public class HolidayIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private HolidayService service;

	@Autowired
	private BuilderFactory builderFactory;

	private HolidayBuilder builder;

	@Before
	public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getHolidayBuilder();
	}
	
	@After
	public void delecteAll() {
		this.builder.deleteAll();
	}
	
	@Test
	public void getHoliday_WithDate_ReturnsHoliday() throws Exception {
		Holiday holidayInDB =this.builder.buildRevolucion_2019().save().getHoliday();
		ResultActions perform = this.mockMvc.perform(get("/api/holidays/{date}", holidayInDB.getDate()));
		expectedPerform(perform,status().isOk(), holidayInDB,null);
	}

	@Test
	public void getHoliday_WithDate_NotFound_Returns404() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/holidays/{date}", LocalDate.of(2019, 5, 27)))
				.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.HOLIDAY_NOT_FOUND));
	}
	
	@Test
	public void getHolidays() throws Exception {
		List<Holiday> savedHolidays = this.createHolidayListSavedToTest();
		ResultActions perform = this.mockMvc.perform(get("/api/holidays"));
		perform.andExpect(status().isOk());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < savedHolidays.size(); i++) {
        	expectedPerform(perform,status().isOk(), savedHolidays.get(i), i);
		}
	}

	private List<Holiday> createHolidayListSavedToTest() {
		List<Holiday> users = new ArrayList<>();
		users.add(this.builder.buildRevolucion_2019().save().getHoliday());
		users.add(this.builder.buildBandera_2019().save().getHoliday());
		users.add(this.builder.buildIndependencia_2019().save().getHoliday());
		return users;
	}
	
	
	@Test
	public void getHolidays_ReturnsNoContent() throws Exception {
		ResultActions perform = this.mockMvc.perform(get("/api/holidays"));
		perform.andExpect(status().isNoContent());
		perform.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void postHoliday_ReturnsHoliday() throws Exception {
		Holiday holidayToSave = this.builder.buildRevolucion_2019().getHoliday();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/holidays").content(asJsonString(holidayToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Holiday retrievedHoliday = this.service.getHolidayByDate(holidayToSave.getDate());
		expectedPerform(perform,status().isCreated(), retrievedHoliday,null);
	}

	@Test
	public void postHoliday_WithDuplicateDate() throws Exception {
		Holiday holidayInDB =this.builder.buildRevolucion_2019().save().getHoliday();
		Holiday holidayToSave = this.builder.buildBandera_2019().getHoliday();
		holidayToSave.setDate(holidayInDB.getDate());
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/holidays").content(asJsonString(holidayToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isConflict());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.HOLIDAY_DUPLICATED));
	}
	
	@Test
	public void postHolidayForYears_ReturnsHolidays() throws Exception {
		Holiday holidayToSave = this.builder.buildRevolucion_2019().getHoliday();
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/api/holidays/{years}",5).content(asJsonString(holidayToSave))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isCreated());
		perform.andExpect(jsonPath("$").isArray());
        for (int i = 0; i < 5; i++) {
        	Holiday retrievedHoliday = this.service.getHolidayByDate(holidayToSave.getDate().plusYears(i));
        	expectedPerform(perform,status().isCreated(), retrievedHoliday,i);
		}
	}

	@Test
	public void putHoliday_WithDate_ReturnsHoliday() throws Exception {
		Holiday holidayInDB =this.builder.buildRevolucion_2019().save().getHoliday();
		holidayInDB.setDescription("Revolucion 1810");
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/holidays/{date}",holidayInDB.getDate()).content(asJsonString(holidayInDB))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		Holiday retrievedHoliday = this.service.getHolidayByDate(holidayInDB.getDate());
		expectedPerform(perform,status().isOk(), retrievedHoliday,null);
	}
	
	@Test
	public void putHoliday_WithDate_NotFound() throws Exception {
		ResultActions perform = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/api/holidays/{date}",LocalDate.of(2019, 5, 25)).content(asJsonString(this.builder.buildRevolucion_2019().getHoliday()))
				   .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		perform.andExpect(status().isNotFound());
		perform.andExpect(jsonPath("error_message").value(BusinessExceptionFactory.HOLIDAY_NOT_FOUND));
	}

	@Test
	public void deleteHoliday_WithDate() throws Exception {
		Holiday holidayInDB =this.builder.buildRevolucion_2019().save().getHoliday();
		ResultActions perform = this.mockMvc.perform(delete("/api/holidays/{date}", holidayInDB.getDate()));
		perform.andExpect(status().isOk());
		Iterable<Holiday> holidays = this.service.getHolidays();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(holidays));
	}

	@Test
	public void deleteHolidays() throws Exception {
		this.createHolidayListSavedToTest();
		ResultActions perform = this.mockMvc.perform(delete("/api/holidays"));
		perform	.andExpect(status().isNoContent());
		Iterable<Holiday> holidays = this.service.getHolidays();
		Assert.assertTrue(IterableUtil.isNullOrEmpty(holidays));
	}
	
	private static void expectedPerform(ResultActions perform, ResultMatcher status, Holiday holiday, Integer idx) throws Exception {
		String arr = idx==null? "": "[" + idx + "].";
		perform	.andExpect(status)
				.andExpect(jsonPath(arr + "date").value(holiday.getDate().toString()))
				.andExpect(jsonPath(arr + "description").value(holiday.getDescription()))
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
