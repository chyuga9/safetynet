package com.openclassrooms.apisafetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.model.Medication;


@Repository
public interface MedicationsRepository extends CrudRepository<Medication, Integer> {

}
