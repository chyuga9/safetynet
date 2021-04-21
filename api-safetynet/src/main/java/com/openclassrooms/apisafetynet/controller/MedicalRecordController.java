package com.openclassrooms.apisafetynet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.service.PersonService;

@RestController
public class MedicalRecordController {

	@Autowired
	private PersonService personService;
	
	@PostMapping("/medicalrecord")
	public Person createMedicalRecord(@RequestBody Person person) {
		return personService.createMedicalRecord(person);
	}
	
    @PutMapping("/medicalrecord/{id_db}")
    public Person updateMedicalRecord(@PathVariable("id_db") String idDb, @RequestBody Person person) {
		return personService.updateMedicalRecord(idDb,person);
	}
}
