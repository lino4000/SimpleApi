package com.lino4000.petFinder.repository;

import org.springframework.data.repository.CrudRepository;

import com.lino4000.petFinder.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public boolean existsByEmail(String email);
	
}
