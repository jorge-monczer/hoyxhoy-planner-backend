package com.hhplanner.entities.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hhplanner.entities.model.User1;

public interface UserRepository extends CrudRepository<User1, String> {

	public Optional<User1> findByUsername(String username);

	public boolean existsByUsername(String username);
	
	@Modifying
	@Query("update User1 u set u.password = :password where u.username = :username")
	public int changePassword(@Param("username") String userName,@Param("password")  String password);

    @Modifying
    @Transactional	
	public void deleteByUsername(String username);
    public void flush();
}
