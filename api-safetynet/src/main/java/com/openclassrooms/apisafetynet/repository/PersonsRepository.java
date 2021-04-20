package com.openclassrooms.apisafetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.model.Person;

@Repository
public interface PersonsRepository extends CrudRepository<Person, String> {

}