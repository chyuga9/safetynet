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
public interface ChildAlert {

	Optional<String> getFirstName();
	Optional<String> getLastName();
	@JsonFormat(pattern="MM/dd/yyyy")
	Optional<Date> getMedicalRecord_Birthdate();
}
