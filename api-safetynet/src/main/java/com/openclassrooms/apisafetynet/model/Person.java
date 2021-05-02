package com.openclassrooms.apisafetynet.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@SqlResultSetMapping(
		name = "PersonCaringByStation",
		entities= {
			@EntityResult(
					entityClass = com.openclassrooms.apisafetynet.model.Person.class,
					fields = {
							@FieldResult(name = "idDb", column = "id_db"),

					})
		})
@NamedNativeQuery(
		name = "Persons",
		query = "SELECT id_db FROM persons WHERE last_name = 'Cooper'", //select persons.first_name, persons.last_name, persons.address, persons.phone FROM persons LEFT JOIN firestations ON firestations.address = persons.address WHERE firestations.station = 3;
		resultSetMapping = "PersonCaringByStation")		
//For query phoneAlert :select  persons.phone FROM persons LEFT JOIN firestations ON firestations.address = persons.address WHERE firestations.station = '3';
// For query personInfo, SELECT persons.last_name, persons.address, medicalrecords.birthdate, persons.email, medicalrecords.medications, medicalrecords.allergies FROM persons JOIN medicalrecords ON persons.id_db = medicalrecords.id_bd WHERE persons.last_name = "Marrack";
// For query email, SELECT persons.email FROM persons;

@Table(name = "persons")
public class Person {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    
    
    @Column(name="id_db")
    private String idDb;
	
	private String address;
	
    private String city;
	
    private String zip;
	
    private String phone;
	
    private String email;
	
	//@JsonFormat(pattern="MM/dd/yyyy")
	//private Date birthdate;
	
	
	
}
