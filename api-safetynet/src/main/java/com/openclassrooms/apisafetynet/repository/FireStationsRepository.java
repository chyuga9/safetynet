package com.openclassrooms.apisafetynet.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.constants.DBConstants;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.FireAlert;
import com.openclassrooms.apisafetynet.projection.People;

@Repository
public interface FireStationsRepository extends CrudRepository<FireStation, Integer> {
	@org.springframework.data.jpa.repository.Query(value = DBConstants.ADDRESSES_BY_STATION, nativeQuery = true)
	//@Query(value="SELECT person.first_name, person.last_name, person.address, person.phone FROM person WHERE firestation.station = ?1  firestation.address = person.address")
	List<Person> findAllByStationNumber(int station);
	//@org.springframework.data.jpa.repository.Query(value = "SELECT p.last_name, p.phone, mr.birthdate, mr.allergies, mr.medications FROM Firestations f JOIN persons p JOIN medicalrecords mr", nativeQuery = true)
	//@org.springframework.data.jpa.repository.Query(value = "SELECT last_name FROM persons f WHERE address = :address")
	//Iterable<FireAlert> getFireAlert(@Param("address")String address);

	Optional<FireStation> findByAddress(String address);

	
}