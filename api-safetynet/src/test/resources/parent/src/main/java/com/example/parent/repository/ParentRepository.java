package com.example.parent.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.parent.model.Parent;

@Repository
public interface ParentRepository extends CrudRepository<Parent,Integer> {

	Optional<Parent> findById(int id);
	Optional<Parent> deleteById(int id);
	
}
