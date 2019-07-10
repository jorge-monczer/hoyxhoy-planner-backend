package com.hhplanner.entities.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hhplanner.entities.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

	public Optional<Project> findByCode(String code);

}
