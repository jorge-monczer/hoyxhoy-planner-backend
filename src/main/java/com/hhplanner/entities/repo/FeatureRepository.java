package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Feature;

public interface FeatureRepository extends CrudRepository<Feature, Integer> {

	public Iterable<Feature> findByProjectId(int projectId);
    public Optional<Feature> findByCodeAndProjectId(String code, int projectId);	
    @Modifying
    @Transactional
    public void deleteByProjectId(int projectId);
    public void flush();
    
}
