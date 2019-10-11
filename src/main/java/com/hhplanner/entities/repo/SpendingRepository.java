package com.hhplanner.entities.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Spending;
import com.hhplanner.entities.model.SpendingId;

@Transactional
public interface SpendingRepository extends CrudRepository<Spending, SpendingId> {

	public Iterable<Spending> findByIdAsignmentIdOrderByIdNumDayAsc(int asignmentId);
    @Modifying
    public void deleteByIdAsignmentId(int asignmentId);
    public void flush();
    
}
