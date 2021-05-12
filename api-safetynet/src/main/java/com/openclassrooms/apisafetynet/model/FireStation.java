package com.openclassrooms.apisafetynet.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "firestations")
// For query fire address, select  medicalrecords.first_name, medicalrecords.last_name, persons.phone, medicalrecords.birthdate, medicalrecords.medications, medicalrecords.allergies, firestations.station FROM medicalrecords JOIN persons ON persons.id_db = medicalrecords.id_bd JOIN firestations ON firestations.address = persons.address WHERE firestations.address = "1509 Culver St";
// For query fire flood, select firestations.address, persons.last_name, persons.phone, medicalrecords.birthdate, medicalrecords.medications, medicalrecords.allergies FROM persons JOIN medicalrecords ON persons.id_db = medicalrecords.id_bd JOIN firestations ON firestations.address = persons.address WHERE firestations.station = 2 ;

public class FireStation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String address;
	
	private int station;
	
	@OneToMany(fetch = FetchType.LAZY)
	//@JoinColumn(name = "person_id", referencedColumnName = "id")
	@JsonIgnore
	private List<Person> persons;
	/*
	@OneToMany
	@PrimaryKeyJoinColumn(name = "address")
	List<Person> persons;
	*/
	public void addPerson(Person person) {
        persons.add(person);
        person.setFireStation(this);
    }
 
    public void removePerson(Person person) {
        persons.remove(person);
        person.setFireStation(null);
    }
	
}
