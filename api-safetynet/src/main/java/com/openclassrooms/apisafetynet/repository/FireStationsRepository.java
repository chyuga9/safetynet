package com.openclassrooms.apisafetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.model.FireStation;

@Repository
public interface FireStationsRepository extends CrudRepository<FireStation, String> {

}