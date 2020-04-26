package com.hhplanner.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class DateUtils {
	public static DayOfWeek[] WORKABLE_DAYS = {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY};

	public static LocalDate plusWorkableDays(LocalDate startDate, int days ) {
		int wdays = startDate.getDayOfWeek().getValue() + days - 1;
		int workableDays = days - 1 + wdays/5*2;
		return startDate.plusDays(workableDays);
	}

}
