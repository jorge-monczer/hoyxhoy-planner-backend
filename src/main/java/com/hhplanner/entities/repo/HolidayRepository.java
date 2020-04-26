package com.hhplanner.entities.repo;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Holiday;

@Transactional
public interface HolidayRepository extends CrudRepository<Holiday, LocalDate> {

	@Cacheable("cacheName")
    @CacheEvict(value = "cacheName", allEntries = true)
	public List<Holiday> findAll();

    @CachePut("cacheName")
	public <S extends Holiday> S save(S entity);

    @CacheEvict(value = "cacheName")
	public void delete(Holiday holiday);


    @CacheEvict(value = "cacheName", allEntries = true)
    public void deleteAll();
    
	public void flush();
}
