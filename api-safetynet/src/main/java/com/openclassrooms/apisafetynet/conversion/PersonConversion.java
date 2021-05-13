package com.openclassrooms.apisafetynet.conversion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.apisafetynet.dto.PersonDto;
import com.openclassrooms.apisafetynet.model.Person;

public class PersonConversion {
	
	@Autowired
	FireStationConversion fsc;
	
	public Person personToDto(Person person2) {
		Person person = new Person();
		person.setFirstName(person2.getFirstName());
		person.setLastName(person2.getLastName());
		//person.setFireStation(fsc.dtoToNormal(person2.getFsd()));
		return person;
	}
	
	public PersonDto dtoToNormal(Person pers) {
		PersonDto person = new PersonDto();
		person.setFirstName(pers.getFirstName());
		person.setLastName(pers.getLastName());
		person.setFsd(fsc.fireStationToDto(pers.getFireStation()));
		return person;
	}
}
