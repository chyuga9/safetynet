package com.openclassrooms.apisafetynet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.service.FireStationService;

@RestController
public class FireStationController {

	@Autowired
	private FireStationService fireStationService;
	
	@PostMapping("/firestation")
	public FireStation createFireStation(@RequestBody FireStation fireStation) {
		return fireStationService.saveFireStation(fireStation);
	}
	
	@DeleteMapping("/firestation/")
	public void deleteFireStation(@RequestBody FireStation fireStation) {
		fireStationService.deleteFireStation(fireStation);
	}
	
	@PutMapping("/firestation")
	public FireStation updateFireStation(@RequestBody FireStation fireStation) {
		return fireStationService.updateFireStation(fireStation);
	}
}
