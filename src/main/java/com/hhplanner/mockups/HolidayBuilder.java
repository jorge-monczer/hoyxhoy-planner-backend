package com.hhplanner.mockups;

import java.time.LocalDate;

import com.hhplanner.entities.exception.BusinessException;
import com.hhplanner.entities.model.Holiday;
import com.hhplanner.entities.service.HolidayService;

public class HolidayBuilder {
	private Holiday holiday;

  	private HolidayService service;
	
	public HolidayBuilder(BuilderFactory builderFactory) {
		this.service = builderFactory.getBeanFactory().getBean(HolidayService.class);
	}
	
	public HolidayBuilder build(int dia, int mounth,int year, String description) {
		this.holiday = new Holiday(LocalDate.of(year, mounth, dia), description);
		return this;
	}
	
	public HolidayBuilder buildRevolucion_2019() {
		return this.build(25,5,2019,"Revolución de Mayo");
	}
	
	public HolidayBuilder buildBandera_2019() {
		return this.build(20,6,2019,"Dia de la Bandera");
	}

	public HolidayBuilder buildIndependencia_2019() {
		return this.build(9,7,2019,"Dia de la Bandera");
	}

	public HolidayBuilder buildCarnaval2020_1() {
		return this.build(24,2,2020,"Carnaval 1");
	}

	public HolidayBuilder buildCarnaval2020_2() {
		return this.build(25,2,2020,"Carnaval 2");
	}
	
	public HolidayBuilder buildRevolucion_2020() {
		return this.build(25,5,2020,"Revolución de Mayo");
	}
	
	public HolidayBuilder buildBandera_2020() {
		return this.build(20,6,2020,"Dia de la Bandera");
	}

	public HolidayBuilder buildIndependencia_2020() {
		return this.build(9,7,2020,"Dia de la Bandera");
	}

	public Holiday getHoliday() {
		if (this.holiday == null) {
			throw new BusinessException("Must build the project");
		}
		return this.holiday;
	}

	public HolidayBuilder save() {
		this.service.saveAndFlush(this.getHoliday());
		return this;
	}
	
	public void deleteAll() {
		this.service.deleteAll();
	}
	
}
