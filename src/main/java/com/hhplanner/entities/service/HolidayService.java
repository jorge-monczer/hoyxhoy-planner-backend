package com.hhplanner.entities.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hhplanner.entities.exception.BusinessExceptionFactory;
import com.hhplanner.entities.model.Holiday;
import com.hhplanner.entities.repo.HolidayRepository;

@Service
@Transactional
public class HolidayService {

	private HolidayRepository holidayRepository;
	
	public HolidayService(HolidayRepository holidayRepository) {
		this.holidayRepository = holidayRepository;
	}

	public Holiday getHolidayByDate(LocalDate date) {
		Optional<Holiday> holiday = this.holidayRepository.findById(date);
		if (!holiday.isPresent()) {
			throw BusinessExceptionFactory.holidayNotFoundException();
		}
		return holiday.get();
	}

	public Iterable<Holiday> getHolidays() {
		return this.holidayRepository.findAll();
	}

	public Holiday save(Holiday holiday) {
		if (this.holidayRepository.existsById(holiday.getDate())) {
			throw BusinessExceptionFactory.holidayDuplicatedException();
		}
		return this.holidayRepository.save(holiday);
	}

	public Holiday saveAndFlush(Holiday holiday) {
		Holiday save = save(holiday);
		this.holidayRepository.flush();
		return save;
	}
	
	public Holiday update(LocalDate date,Holiday holiday) {
		if (!this.holidayRepository.existsById(date)) {
			throw BusinessExceptionFactory.holidayNotFoundException();
		}
		holiday.setDate(date);
		return this.holidayRepository.save(holiday);
	}

	public void delete(LocalDate date) {
		this.holidayRepository.deleteById(date);
	}

	public void deleteAll() {
		this.holidayRepository.deleteAll();
	}

}
