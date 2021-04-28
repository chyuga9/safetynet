package com.openclassrooms.apisafetynet.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.constants.DBConstants;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.Person;

@Repository
public interface FireStationsRepository extends CrudRepository<FireStation, String> {
	@org.springframework.data.jpa.repository.Modifying
	@org.springframework.data.jpa.repository.Query(value = DBConstants.ADDRESSES_BY_STATION, nativeQuery = true)
	//@Query(value="SELECT person.first_name, person.last_name, person.address, person.phone FROM person WHERE firestation.station = ?1  firestation.address = person.address")
	List<Person> findAllByStationNumber(int station);
	
}