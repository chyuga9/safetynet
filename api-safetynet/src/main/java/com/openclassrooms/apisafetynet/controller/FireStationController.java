package com.openclassrooms.apisafetynet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.PeopleAndCount;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.FireAlert;
import com.openclassrooms.apisafetynet.projection.People;
import com.openclassrooms.apisafetynet.service.FireStationService;

@RestController
public class FireStationController {

	@Autowired
	private FireStationService fireStationService;
	
	 
	//---------- Méthodes de base --------
	
	@DeleteMapping("/firestation")
	public void deleteFireStation( @RequestBody FireStation fireStation) {
		fireStationService.deleteFireStation(fireStation);
	}
	
	@GetMapping("/firestation")
    public Iterable<FireStation> getFireStations() {
        return fireStationService.getFireStations();
    }
	
	@PostMapping("/firestation")
	public FireStation createFireStation(@RequestBody FireStation fireStation) {
		return fireStationService.saveFireStation(fireStation);
	}
	
	@PostMapping("/firestations")
	public Iterable<FireStation> createMedicalRecords(@RequestBody Iterable<FireStation> fireStations) {
		return fireStationService.saveFireStations(fireStations);
	}
	
	@PutMapping("/firestation")
	public FireStation updateFireStation(@RequestBody FireStation fireStation) {
		return fireStationService.updateFireStation(fireStation);
	}
	
	//---------- Méthodes personalisées --------

	
	
	@GetMapping(value = "/firestation", params = "stationNumber")
	 public PeopleAndCount getPeople(@RequestParam("stationNumber") int station) {
       return fireStationService.getPeople(station);
   }
	
	@GetMapping(value = "/fire", params = "address")
    public Iterable<FireAlert> getFireAlert(@RequestParam("address") String address) {
        return fireStationService.getFireAlert(address);
    }
	
	@GetMapping(value = "/flood/stations", params = "stations")
    public Iterable<FireAlert> getFloodAlert(@RequestParam("stations") int[] stations) {
        return fireStationService.getFloodAlert(stations);
    }
	/*
	
	
	@GetMapping("/firestation")
    public List<Person> getAddressesByStationNumber(@RequestParam("stationNumber") int station) {
       
     return fireStationService.getAddressesByStationNumber(station);
    }
       @GetMapping("/fire")
    public Iterable<FireAlert>(@RequestParam("address") String address){
    	fireStationService.getFireAlert(address);
    }
    */
	
	
	
	

	
}
