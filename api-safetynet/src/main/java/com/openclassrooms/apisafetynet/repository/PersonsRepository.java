package com.openclassrooms.apisafetynet.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.constants.DBConstants;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.ChildAlert;
import com.openclassrooms.apisafetynet.projection.Email;
import com.openclassrooms.apisafetynet.projection.FireAlert;
import com.openclassrooms.apisafetynet.projection.People;
import com.openclassrooms.apisafetynet.projection.PersonInfo;
import com.openclassrooms.apisafetynet.projection.PhoneAlert;

@Repository
public interface PersonsRepository extends CrudRepository<Person, Integer> {

	Optional<Person> findByIdDb(String idDb);
	Optional<Person> deleteByIdDb(String idDb);
	Iterable<ChildAlert> findByAddressAndMedicalRecord_BirthdateBefore(String lastName, Date date);
	Iterable<ChildAlert> findByAddressAndMedicalRecord_BirthdateAfter(String address, Date date);
	Iterable<Person> findByLastNameAndAddressAndFirstNameNot(Optional<String> lastName, String address, Optional<String> firstName);
	//@Query(value = "SELECT p.phone FROM persons p LEFT JOIN firestations f ON p.address = f.address WHERE f.station = ?1")
	<T>Iterable<T> findByFireStation_Station(int station,Class<T> type);
	Iterable<PersonInfo> findByLastName(String lastName);
	Iterable<Email> findByCity(String city);
	int countByFireStation_StationAndMedicalRecord_BirthdateBefore(int station, Date date);
	int countByFireStation_StationAndMedicalRecord_BirthdateAfter(int station, Date date);
	//Iterable<People> findByFireStation_Station1(int station);
	<T>Iterable<T> findByAddress(String address,Class<T> type);
	int countByFireStation_Station(int station);

}