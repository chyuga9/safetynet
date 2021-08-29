package com.example.parent.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.parent.model.Child;
import com.example.parent.model.Parent;

@Repository
public interface ChildRepository extends CrudRepository<Child,Integer> {

	Optional<Child> findById(int id);
	Optional<Child> deleteById(int id);
	
}
