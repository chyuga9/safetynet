package com.openclassrooms.apisafetynet.projection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface MedicalRecordProjection {

	Optional<Date> getBirthdate();
	Optional<ArrayList<String>> getMedications();
	Optional<ArrayList<String>> getAllergies();
}
