package com.openclassrooms.apisafetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.model.MedicalRecord;

@Repository
public interface MedicalRecordsRepository extends CrudRepository<MedicalRecord, String> {

}