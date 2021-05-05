package com.openclassrooms.apisafetynet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.constants.DBConstants;
import com.openclassrooms.apisafetynet.dto.ResponseDto;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;

@Repository
public interface PersonsRepository extends CrudRepository<Person, String> {
	//@org.springframework.data.jpa.repository.Query(value = DBConstants.ADDRESSES_BY_STATION, nativeQuery = true)
	//@Query(value="SELECT person.first_name, person.last_name, person.address, person.phone FROM person WHERE firestation.station = ?1  firestation.address = person.address")
	//Iterable<Person> findAllByStationNumber(String station);
	//@org.springframework.data.jpa.repository.Query(value = DBConstants.SEARCH_IN_MEDICAL_RECORDS, nativeQuery = true)
	//String  findByIdDb(String IdDb);
	@org.springframework.data.jpa.repository.Query(value = DBConstants.TEST_JOINT_TABLE, nativeQuery = true)
	void createRowInJointTable(String mr_id, String idBd);
	@org.springframework.data.jpa.repository.Query(value = DBConstants.TEST_B, nativeQuery = true)
	//@Query(DBConstants.TEST_B)
	Iterable<ResponseDto> test();
	Optional<Person> findByIdDb(String idDb);
	Iterable<Person> findByAddress(String address);
	

}