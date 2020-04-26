package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Spring;

@Transactional
public interface SpringRepository extends CrudRepository<Spring, Integer> {

	public Iterable<Spring> findByProjectIdOrderByCode(int projectId);
    public Optional<Spring> findByCodeAndProjectId(String code, int projectId);	
    @Modifying
    public void deleteByProjectId(int projectId);
    public void flush();
    
}
