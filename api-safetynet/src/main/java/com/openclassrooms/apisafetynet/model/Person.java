package com.openclassrooms.apisafetynet.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "persons")
public class Person {
	
	
	@Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    
    @Id
    @Column(name="id_db")
    private String idDb;
	
	private String address;
	
	private String city;
	
	private int zip;
	
	private String phone;
	
	private String email;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date birthdate;
	
	private ArrayList<Medication> medications;
	
	private ArrayList<Allergie> allergies;

	
}
