package com.openclassrooms.apisafetynet.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
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
    @JsonIgnore
    private String idDb;
	
    private String address;
	
    @ManyToOne
	@JoinColumn(name = "firestation_id",referencedColumnName = "id")
    @JsonIgnore
    private FireStation fireStation;
	
    private String city;
	
    private String zip;
	
    private String phone;
	
    private String email;
    
   @OneToOne
   @JoinColumn(name = "medicalrecord_id", referencedColumnName = "id")
   @JsonIgnore
   private MedicalRecord medicalRecord;

public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.city = city;
	this.zip = zip;
	this.phone = phone;
	this.email = email;
}

public Person() {
	super();
}
	
}
