package com.hhplanner.entities.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Spending;
import com.hhplanner.entities.model.SpendingId;

@Transactional
public interface SpendingRepository extends CrudRepository<Spending, SpendingId> {

	public Iterable<Spending> findByIdAsignmentIdOrderByIdNumDayAsc(int asignmentId);
    @Modifying
    public void deleteByIdAsignmentId(int asignmentId);
    public void flush();

//    @Query("SELECT s.id.asignment.spring as spring, SUM(s.spent) as spending, 0.0 as advance from Spending s where s.id.asignment.spring.project.id != ?1")
    @Query(value = "SELECT sp.code, sp.name, SUM(s.spent) as spent " 
        	+  " from spending s, asignment a, spring sp" 
        	+  " where s.asignment_id = a.id " 
        	+   " and a.spring_id = sp.id " 
        	+   " and sp.project_id = ?1 "
        	+   " group by sp.code, sp.name ", nativeQuery = true)
	public List<Q_SpentSpringSummary> sumSpentGroupBySpring(int projectId);
//	public List<Tuple> sumSpentGroupBySpring(int projectId);
	
	public interface Q_SpentSpringSummary {
	    String getCode();
	    String getName();
	    Double getSpent();
	}

    
}
