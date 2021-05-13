package com.openclassrooms.apisafetynet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.constants.DBConstants;
import com.openclassrooms.apisafetynet.model.MedicalRecord;

@Repository
public interface MedicalRecordsRepository extends CrudRepository<MedicalRecord, String> {
	//     A CHANGER
	//@org.springframework.data.jpa.repository.Query(value = DBConstants.TEST_A, nativeQuery = true)
	//Iterable<MedicalRecord> findAllByAddressAndStartBirthdateAfter(String station);
	/*
	@org.springframework.data.jpa.repository.Query(value = DBConstants.SEARCH_IN_PERSONS, nativeQuery = true)
	String findByIdDb(String IdDb);
	@org.springframework.data.jpa.repository.Query(value = DBConstants.TEST_JOINT_TABLE, nativeQuery = true)
	void createRowInJointTable(String mr_id, String idBd);
*/
}