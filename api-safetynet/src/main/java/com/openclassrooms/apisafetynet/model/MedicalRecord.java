package com.openclassrooms.apisafetynet.model;

import java.util.ArrayList;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
//For query child alert (select  persons.first_name, persons.last_name, medicalrecords.birthdate FROM persons LEFT JOIN medicalrecords ON medicalrecords.id_bd = persons.id_db WHERE persons.address = '1509 Culver St' AND medicalrecords.birthdate > "2003-04-27" ;
// Du coup pour la liste des personnes qui vivent avec les enfants, est ce que je dois faire une deuxieme requete ? 
@Table(name = "medicalrecords")
public class MedicalRecord {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    
    public MedicalRecord(String firstName, String lastName, String idBd, Date birthdate, String medications,
    		String allergies, Person person) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.idBd = idBd;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
		this.person = person;
	}

	public MedicalRecord(String firstName, String lastName, String idBd, Date birthdate, String medications,
			String allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.idBd = idBd;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public MedicalRecord() {
		super();
	}

	@Column(name = "id_bd")
    @JsonIgnore
    private String idBd;
    
    
    //@OneToOne(mappedBy = "medicalRecord")
    //private Person person;
	
	@JsonFormat(pattern="MM/dd/yyyy")
	private Date birthdate;
	
	private String medications;
	
	private String allergies;
	
	@OneToOne
	@JsonIgnore
	private Person person;
	
}
