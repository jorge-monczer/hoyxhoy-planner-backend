package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Project;

@Transactional
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    public void flush();
	public Optional<Project> findByCode(String code);

}
