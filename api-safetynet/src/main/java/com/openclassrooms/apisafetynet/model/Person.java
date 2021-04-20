package com.openclassrooms.apisafetynet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "persons")
public class Person {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
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

	
}
