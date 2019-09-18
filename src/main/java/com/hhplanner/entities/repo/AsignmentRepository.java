package com.hhplanner.entities.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Asignment;
import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.User1;

public interface AsignmentRepository extends CrudRepository<Asignment, Integer> {

	public List<Asignment> findBySpringIdOrderByUserAscFeatureAsc(int springId);
	public Iterable<Asignment> findByUserAndSpringId(User1 user, int springId);
	public Optional<Asignment> findByFeatureId(int featureId);	
	public boolean existsByFeatureCode(String featureCode);	
    public Optional<Asignment> findByFeature(Feature feature);	
    @Modifying
    @Transactional
    public void deleteBySpringId(int springId);
    public void flush();
    
    @Query("SELECT SUM(a.feature.estimatedHours) from Asignment a where a.user = ?1 and a.spring.id = ?2 and a.id != ?3")
	public Integer sumEstimatedHoursByUserAndSpringIdButNotMe(User1 user, int springId, int id);
   
    @Query(value = "SELECT a.username as username, c.available_hours as availableHours, SUM(f.estimated_hours) as estimatedHours " 
    	+  " from asignment a, capacity c, feature f " 
    	+  " where a.spring_id = c.spring_id " 
    	+   " and a.username = c.username "  
    	+   " and a.feature_id = f.id "
    	+   " and a.spring_id = ?1 "
    	+   " group by a.username,c.available_hours ", nativeQuery = true)
 	public List<Q_CapacitySumary> sumHoursAsignedForUserBySpringId(int springId);
    
 	public interface Q_CapacitySumary {
 	    String getUsername();
 	    Integer getAvailableHours();
 	    Integer getEstimatedHours();
 	}
}
