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

import com.fasterxml.jackson.annotation.JsonFormat;

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
    
    @Column(name = "id_bd")
    private String idBd;
    
    @OneToOne
    @PrimaryKeyJoinColumn(name = "id_db")
    private Person person;
	
	@JsonFormat(pattern="MM/dd/yyyy")
	private Date birthdate;
	
	private ArrayList<String> medications;
	
	private ArrayList<String> allergies;
	
}
