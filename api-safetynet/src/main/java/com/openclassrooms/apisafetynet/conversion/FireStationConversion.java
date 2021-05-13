package com.openclassrooms.apisafetynet.conversion;

import com.openclassrooms.apisafetynet.dto.FireStationDto;
import com.openclassrooms.apisafetynet.model.FireStation;

public class FireStationConversion {
	
	FireStationDto fireStationToDto(FireStation fs) {
		FireStationDto fsd = new FireStationDto();
		fsd.setAddress(fs.getAddress());
		fsd.setStation(fs.getStation());
		return fsd;
	}
	FireStation dtoToNormal(FireStationDto fsd) {
		FireStation fs = new FireStation();
		fs.setAddress(fsd.getAddress());
		fs.setStation(fsd.getStation());
		return fs;
	}
}
