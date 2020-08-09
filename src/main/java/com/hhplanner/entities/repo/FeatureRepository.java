package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Feature;
import com.hhplanner.entities.model.User1;

public interface FeatureRepository extends CrudRepository<Feature, Integer> {

	public Iterable<Feature> findByProjectId(int projectId);
    public Optional<Feature> findByCodeAndProjectId(String code, int projectId);	
    @Modifying
    @Transactional
    public void deleteByProjectId(int projectId);
    public void flush();
    
//    @Query("SELECT f from Features f where f.project.id = ?1 and not exists ( select a from Asignment a where a.feature = f ) ")
    @Query("SELECT f from Feature f where f.project.id = ?1 and not exists (select a from Asignment a where a.feature = f )")
    public Iterable<Feature> findToAsignByProjectId(int projectId);

    @Query("SELECT f.project.code as projectCode, f.project.name as projectName, SUM(f.estimatedHours) as estimated from Feature f where f.project.id = ?1 group by f.project.code, f.project.name")
	public Q_EstimatedProject sumEstimatedProject(int projectId);

 	public interface Q_EstimatedProject {
 	    String getProjectCode();
 	    String getProjectName();
 	    Double getEstimated();
 	}
    
}
