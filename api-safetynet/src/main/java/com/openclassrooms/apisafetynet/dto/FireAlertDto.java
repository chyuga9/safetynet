package com.openclassrooms.apisafetynet.dto;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FireAlertDto {

	private Optional<String> lastName;
	
	private Optional<String> getPersons_Phone;
	@JsonFormat(pattern="MM/dd/yyyy")
	private Optional<Date> getMedicalRecord_Birthdate;
	private Optional<List<String>> getMedicalRecord_Allergies;
	private Optional<List<String>> getMedicalRecord_Medications;
	
	}
