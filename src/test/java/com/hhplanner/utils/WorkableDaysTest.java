package com.hhplanner.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hhplanner.HoyxhoyPlannerBackendApplication;
import com.hhplanner.entities.model.Holiday;
import com.hhplanner.entities.model.WorkableDays;
import com.hhplanner.mockups.BuilderFactory;
import com.hhplanner.mockups.HolidayBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes =  HoyxhoyPlannerBackendApplication.class
)
public class WorkableDaysTest {

	@Autowired
	private BuilderFactory builderFactory;

	private HolidayBuilder builder;

	@Before
	public void init() {
		this.builderFactory.init();
		this.builder = this.builderFactory.getHolidayBuilder();
	}

	@Test
	public void workable_FromMonday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.JANUARY, 14), 7)).isEqualTo(LocalDate.of(2019, Month.JANUARY, 22));
	}
	
	@Test
	public void workable_FromMonday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.JANUARY, 14), 22)).isEqualTo(LocalDate.of(2019, Month.FEBRUARY, 12));
	}

	@Test
	public void workable_FromWednesday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.FEBRUARY, 20), 7)).isEqualTo(LocalDate.of(2019, Month.FEBRUARY, 28));
	}

	@Test
	public void workable_FromWednesday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.FEBRUARY, 20), 22)).isEqualTo(LocalDate.of(2019, Month.MARCH, 21));
	}

	@Test
	public void workable_FromFriday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.MARCH, 15), 7)).isEqualTo(LocalDate.of(2019, Month.MARCH, 25));
	}

	@Test
	public void workable_FromFriday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.MARCH, 15), 22)).isEqualTo(LocalDate.of(2019, Month.APRIL, 15));
	}

	@Test
	public void mowefri_FromMonday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.WEDNESDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.APRIL, 15), 7)).isEqualTo(LocalDate.of(2019, Month.APRIL, 29));
	}

	@Test
	public void mowefri_FromMonday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.WEDNESDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.APRIL, 15), 22)).isEqualTo(LocalDate.of(2019, Month.JUNE, 3));
	}

	@Test
	public void mowefri_FromWednesday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.WEDNESDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.MAY, 15), 7)).isEqualTo(LocalDate.of(2019, Month.MAY, 29));
	}

	@Test
	public void mowefri_FromWednesday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.WEDNESDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.MAY, 15), 22)).isEqualTo(LocalDate.of(2019, Month.JULY, 3));
	}

	@Test
	public void mowefri_FromFriday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.WEDNESDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.JUNE, 14), 7)).isEqualTo(LocalDate.of(2019, Month.JUNE, 28));
	}

	@Test
	public void mowefri_FromFriday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.WEDNESDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.JUNE, 14), 22)).isEqualTo(LocalDate.of(2019, Month.AUGUST, 2));
	}

	@Test
	public void tuthu_FromTuesday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.JULY, 16), 7)).isEqualTo(LocalDate.of(2019, Month.AUGUST, 6));
	}
	
	@Test
	public void tuthu_FromTuesday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.JULY, 16), 22)).isEqualTo(LocalDate.of(2019, Month.SEPTEMBER, 26));
	}
	
	@Test
	public void tuthu_FromThursday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.AUGUST, 8), 7)).isEqualTo(LocalDate.of(2019, Month.AUGUST, 29));
	}
	
	@Test
	public void tuthu_FromThursday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.AUGUST, 8), 22)).isEqualTo(LocalDate.of(2019, Month.OCTOBER, 22));
	}
	
	@Test
	public void motuthu_FromMonday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.SEPTEMBER, 16), 7)).isEqualTo(LocalDate.of(2019, Month.SEPTEMBER, 30));
	}
	
	@Test
	public void motuthu_FromMonday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.SEPTEMBER, 16), 22)).isEqualTo(LocalDate.of(2019, Month.NOVEMBER, 4));
	}

	@Test
	public void motuthu_FromTuersday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.OCTOBER, 15), 7)).isEqualTo(LocalDate.of(2019, Month.OCTOBER, 29));
	}

	@Test
	public void motuthu_FromTuersday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.OCTOBER, 15), 22)).isEqualTo(LocalDate.of(2019, Month.DECEMBER, 3));
	}
	
	@Test
	public void motuthu_FromThursday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.OCTOBER, 17), 7)).isEqualTo(LocalDate.of(2019, Month.OCTOBER, 31));
	}

	@Test
	public void motuthu_FromThursday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.OCTOBER, 17), 22)).isEqualTo(LocalDate.of(2019, Month.DECEMBER, 5));
	}

	@Test
	public void sasu_FromSaturday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 2), 7)).isEqualTo(LocalDate.of(2019, Month.NOVEMBER, 23));
	}

	@Test
	public void sasu_FromSaturday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 2), 22)).isEqualTo(LocalDate.of(2020, Month.JANUARY, 12));
	}

	@Test
	public void sasu_FromSunday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 3), 7)).isEqualTo(LocalDate.of(2019, Month.NOVEMBER, 24));
	}

	@Test
	public void sasu_FromSunday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 3), 22)).isEqualTo(LocalDate.of(2020, Month.JANUARY, 18));
	}

	@Test
	public void wesasu_FromWednesday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.WEDNESDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 6), 7)).isEqualTo(LocalDate.of(2019, Month.NOVEMBER, 20));
	}

	@Test
	public void wesasu_FromWednesday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.WEDNESDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 6), 22)).isEqualTo(LocalDate.of(2019, Month.DECEMBER, 25));
	}

	@Test
	public void wesasu_FromSunday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.WEDNESDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 3), 7)).isEqualTo(LocalDate.of(2019, Month.NOVEMBER, 17));
	}

	@Test
	public void wesasu_FromSunday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.WEDNESDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2019, Month.NOVEMBER, 3), 22)).isEqualTo(LocalDate.of(2019, Month.DECEMBER, 22));
	}

	@Test
	public void workableBisiesto_FromMonday_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 17), 7)).isEqualTo(LocalDate.of(2020, Month.FEBRUARY, 25));
	}
	
	@Test
	public void workableBisiesto_FromMonday_plus22Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 17), 22)).isEqualTo(LocalDate.of(2020, Month.MARCH, 17));
	}
	
	@Test
	public void workable_FromMonday_withCarnavalHolidays_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		wd.setHolidays(createHolidays());
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 17), 7)).isEqualTo(LocalDate.of(2020, Month.FEBRUARY, 27));
	}

	@Test
	public void workable_FromMonday_withCarnavalHolidays_plus6Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		wd.setHolidays(createHolidays());
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 17), 6)).isEqualTo(LocalDate.of(2020, Month.FEBRUARY, 26));
	}

	@Test
	public void workable_FromWednesday_withCarnavalHolidays_plus7Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		wd.setHolidays(createHolidays());
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 19), 7)).isEqualTo(LocalDate.of(2020, Month.MARCH, 2));
	}
	
	@Test
	public void workable_FromFriday_withCarnavalHolidays_plus2Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY});
		wd.setHolidays(createHolidays());
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 21), 2)).isEqualTo(LocalDate.of(2020, Month.FEBRUARY, 26));
	}

	@Test
	public void motuth_FromMonday_withCarnavalHolidays_plus6Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		wd.setHolidays(createHolidays());
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 17), 6)).isEqualTo(LocalDate.of(2020, Month.MARCH, 3));
	}
	
	@Test
	public void motuth_FromTuesday_withCarnavalHolidays_plus3Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		wd.setHolidays(createHolidays());
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 18), 3)).isEqualTo(LocalDate.of(2020, Month.FEBRUARY, 27));
	}
	
	@Test
	public void motuth_FromThursday_withCarnavalHolidays_plus2Days() {
		WorkableDays wd = new WorkableDays(new DayOfWeek[] {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.THURSDAY});
		wd.setHolidays(createHolidays());
		assertThat(wd.plusWorkableDays(LocalDate.of(2020, Month.FEBRUARY, 20), 2)).isEqualTo(LocalDate.of(2020, Month.FEBRUARY, 27));
	}
 
	private List<Holiday> createHolidays() {
		List<Holiday> holidays = new ArrayList<>();
		holidays.add(this.builder.buildRevolucion_2019().getHoliday());
		holidays.add(this.builder.buildBandera_2019().getHoliday());
		holidays.add(this.builder.buildIndependencia_2019().getHoliday());
		holidays.add(this.builder.buildCarnaval2020_1().getHoliday());
		holidays.add(this.builder.buildCarnaval2020_2().getHoliday());
		holidays.add(this.builder.buildRevolucion_2020().getHoliday());
		holidays.add(this.builder.buildBandera_2020().getHoliday());
		holidays.add(this.builder.buildIndependencia_2020().getHoliday());
		return holidays;
	}
	
}
