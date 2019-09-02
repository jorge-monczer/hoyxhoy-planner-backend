package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;

public interface AsignmentRepository extends CrudRepository<Asignment, Integer> {

	public Iterable<Asignment> findBySpringId(int springId);
	public Optional<Asignment> findByFeatureId(int featureId);	
    public Optional<Asignment> findByFeature(Feature feature);	
    @Modifying
    @Transactional
    public void deleteBySpringId(int springId);
    public void flush();
    
}
