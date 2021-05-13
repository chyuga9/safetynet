package com.openclassrooms.apisafetynet.projection;

import java.sql.Date;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface PersonInfo {

	Optional<String> getLastName();
	Optional<String> getAddress();
	@JsonFormat(pattern="MM/dd/yyyy")
	Optional<Date> getMedicalRecord_Birthdate();
	Optional<String> getEmail();
	Optional<List<String>> getMedicalRecord_Allergies();
	Optional<List<String>> getMedicalRecord_Medications();
}
