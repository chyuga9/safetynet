package com.openclassrooms.apisafetynet.dto;

import lombok.Data;

@Data
public class PersonDto {

	private String firstName;
	private String lastName;
	private FireStationDto fsd;
}
