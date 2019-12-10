package com.hhplanner.entities.repo;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Holiday;

@Transactional
public interface HolidayRepository extends CrudRepository<Holiday, LocalDate> {

    public void flush();
}
