package com.openclassrooms.apisafetynet.controller;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.openclassrooms.apisafetynet.exceptions.UnfindablePersonException;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.service.MedicalRecordService;
import com.openclassrooms.apisafetynet.service.PersonService;

import javassist.NotFoundException;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@GetMapping("/medicalrecord")
    public ResponseEntity<Iterable<MedicalRecord>> getMedicalRecords() {
        return ResponseEntity.ok().body(medicalRecordService.getMedicalRecords());
    }
	/*
	@GetMapping("/childAlert")
	public Iterable<MedicalRecord> getChildAndFamily(@RequestParam ("address") String address){
		return medicalRecordService.searchChild(address);
	}
	*/
	@PostMapping("/medicalrecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws UnfindablePersonException {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicalRecord.getId()).toUri();
		return ResponseEntity.created(location).body(medicalRecordService.createMedicalRecord(medicalRecord));
	}
	@PostMapping("/medicalrecords")
	public ResponseEntity<Iterable<MedicalRecord>> createMedicalRecords(@RequestBody Iterable<MedicalRecord> medicalRecords) throws UnfindablePersonException {
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/packdemedicalrecord").build(false).toUri();
        return ResponseEntity.created(location).body(medicalRecordService.createMedicalRecords(medicalRecords));
		}
	
	
    @PutMapping("/medicalrecord/{id_db}")
    public MedicalRecord updateMedicalRecord(@PathVariable("id_db") String idDb, @RequestBody MedicalRecord medicalRecord) {
		return medicalRecordService.updateMedicalRecord(idDb,medicalRecord);
	}
    @DeleteMapping("/medicalrecord/{id_bd}")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable("id_bd") String idDb) throws NotFoundException {
    	medicalRecordService.deleteMedicalRecord(idDb);
    	return ResponseEntity.ok().body("Medical Record deleted");
    }
}
