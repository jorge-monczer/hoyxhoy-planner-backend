package com.hhplanner.entities.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SimpleRepository<T> extends CrudRepository<T, Integer>{

	public Optional<T> findByCode(String code);

}
