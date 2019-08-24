package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Spring;

public interface SpringRepository extends CrudRepository<Spring, Integer> {

	public Iterable<Spring> findByProjectIdOrderByStartDate(int projectId);
    public Optional<Spring> findByCodeAndProjectId(String code, int projectId);	
    @Modifying
    @Transactional
    public void deleteByProjectId(int projectId);
    public void flush();
    
}
