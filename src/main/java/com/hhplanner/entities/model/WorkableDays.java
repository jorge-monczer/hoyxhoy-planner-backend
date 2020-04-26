package com.hhplanner.entities.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkableDays {
	private DayOfWeek[] workableDays;
    private List<Holiday> holidays = new ArrayList<>();
	
	public WorkableDays(DayOfWeek[] workableDays) {
		this.workableDays = workableDays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	public LocalDate plusWorkableDays(LocalDate startDate, int days) {
		LocalDate endDate = this.plusInnerWorkableDays(startDate, days);
		days = this.getHolidaysInRange(startDate, endDate).size();
		if (days > 0) {
			endDate = this.plusWorkableDays(endDate, days + 1);			
		}
		return endDate;
	}
	
	private LocalDate plusInnerWorkableDays(LocalDate startDate, int days ) {
		int spring_days = days - 1;
		int weeks = spring_days / this.workableDays.length;
		int rest = spring_days % this.workableDays.length;
		int idx_dayOfWeek = Arrays.binarySearch(this.workableDays, startDate.getDayOfWeek());
		int plus_days = 0;
		for (int i = 1; i <= rest; i++) {
			plus_days = plus_days + getNextWorkableDayValue(idx_dayOfWeek) - this.workableDays[idx_dayOfWeek].getValue();
			idx_dayOfWeek = getNextWorkableDayIndex(idx_dayOfWeek);
		}
		int workableDays = weeks * 7 + plus_days;
		return startDate.plusDays(workableDays);
	}
	
	private int getNextWorkableDayValue(int idx_dayOfWeek) {
		int idx_nextDayOfWeek = getNextWorkableDayIndex(idx_dayOfWeek);
		if (idx_nextDayOfWeek == 0) {
			return this.workableDays[idx_nextDayOfWeek].getValue() + 7;
		}
		return this.workableDays[idx_nextDayOfWeek].getValue();
	}
	
	private int getNextWorkableDayIndex(int idx_dayOfWeek) {
		int idx_nextDayOfWeek = idx_dayOfWeek +1;
		if (this.workableDays.length == idx_nextDayOfWeek) {
			idx_nextDayOfWeek = 0;
		}
		return idx_nextDayOfWeek;
	}

    private List<Holiday> getHolidaysInRange(LocalDate start, LocalDate end) {
    	return holidays.stream()
                .filter(holiday -> filterHolidays(holiday,start,end))
                .collect(Collectors.toList());
    }
	
    private boolean filterHolidays(Holiday holiday, LocalDate start, LocalDate end) {
    	return Arrays.binarySearch(this.workableDays, holiday.getDate().getDayOfWeek()) >= 0
    		&& holiday.getDate().isAfter(start) && (holiday.getDate().isBefore(end) || holiday.getDate().isEqual(end));
    }
	

}
