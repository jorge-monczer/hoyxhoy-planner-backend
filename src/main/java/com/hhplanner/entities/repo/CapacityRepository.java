package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Capacity;
import com.hhplanner.entities.model.User1;

public interface CapacityRepository extends CrudRepository<Capacity, Integer> {

	public Iterable<Capacity> findBySpringId(int springId);
	public Optional<Capacity> findByUserUsername(String username);	
    public Optional<Capacity> findByUserAndSpringId(User1 user, int springId);	
    @Modifying
    @Transactional
    public void deleteBySpringId(int springId);
    public void flush();
    
}
