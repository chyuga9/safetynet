package com.openclassrooms.apisafetynet.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.service.MedicalRecordService;
import com.openclassrooms.apisafetynet.service.PersonService;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@GetMapping("/medicalrecord")
    public Iterable<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.getMedicalRecords();
    }
	/*
	@GetMapping("/childAlert")
	public Iterable<MedicalRecord> getChildAndFamily(@RequestParam ("address") String address){
		return medicalRecordService.searchChild(address);
	}
	*/
	@PostMapping("/medicalrecord")
	public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		return medicalRecordService.createMedicalRecord(medicalRecord);
	}
	@PostMapping("/medicalrecords")
	public Iterable<MedicalRecord> createMedicalRecords(@RequestBody Iterable<MedicalRecord> medicalRecord) {
		return medicalRecordService.createMedicalRecords(medicalRecord);
	}
	
	
    @PutMapping("/medicalrecord/{id_db}")
    public MedicalRecord updateMedicalRecord(@PathVariable("id_db") String idDb, @RequestBody MedicalRecord medicalRecord) {
		return medicalRecordService.updateMedicalRecord(idDb,medicalRecord);
	}
    @DeleteMapping("/medicalrecord/{id_bd}")
    public void deletePerson(@PathVariable("id_bd") String idDb) {
    	medicalRecordService.deleteMedicalRecord(idDb);
    }
}
