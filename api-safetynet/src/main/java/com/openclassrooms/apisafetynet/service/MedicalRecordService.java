package com.openclassrooms.apisafetynet.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.model.Allergie;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Medication;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.MedicalRecordsRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;
    /*
    public Optional<MedicalRecord> getMedicalRecord(final Long id) {
        return medicalRecordsRepository.findById(id);
    }

    public Iterable<MedicalRecord> getMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }

    public void deleteMedicalRecord(final Long id) {
    	medicalRecordsRepository.deleteById(id);
    }
    
    public MedicalRecord saveMedicalRecord(MedicalRecord medicaRecord) {
    	MedicalRecord savedMedicalRecord = medicalRecordsRepository.save(medicaRecord);
        return savedMedicalRecord;
    }
*/
    public Optional<MedicalRecord> getMedicalRecord(final String idDb) {
        return medicalRecordsRepository.findById(idDb);
    }

    public Iterable<MedicalRecord> getMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }
    
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
    	String idBd = medicalRecord.getFirstName() + "_" + medicalRecord.getLastName();
    	medicalRecord.setIdBd(idBd);
    	MedicalRecord savedMedicalRecord= medicalRecordsRepository.save(medicalRecord);

    	ArrayList<String> medications = medicalRecord.getMedications();
    	ArrayList<String> allergies = medicalRecord.getAllergies();
    	/*
    	if(medication != null) {
    		savedPerson.setMedications(medication);
    		//medicationsRepository.saveAll(medication);
    		}
    	if(allergie != null) {
    		savedPerson.setAllergies(allergie);
    		//allergiesRepository.saveAll(allergie);
    		}
    		*/
        return savedMedicalRecord;
        
	
	}
    // Pour rentrer les données plus vite pour moi
    public Iterable<MedicalRecord> createMedicalRecords(Iterable<MedicalRecord> medicalRecords) {
    	for(MedicalRecord medRec : medicalRecords) {
    	String idBd = medRec.getFirstName() + "_" +  medRec.getLastName();
    	medRec.setIdBd(idBd);
    	medicalRecordsRepository.save(medRec);
    	}
    	
    	 return medicalRecords;
        }
    
	public MedicalRecord updateMedicalRecord(String idDb, MedicalRecord medicalRecord) {
		Optional<MedicalRecord> updatedMedicalRecord = medicalRecordsRepository.findById(idDb);
    	ArrayList<String> medication = medicalRecord.getMedications();
    	ArrayList<String> allergie = medicalRecord.getAllergies();
    	Date birthdate = medicalRecord.getBirthdate();
    	if(birthdate != null) {
    		updatedMedicalRecord.get().setBirthdate(birthdate);}
    	if(medication != null) {
    		updatedMedicalRecord.get().setMedications(medication);
    		//medicationsRepository.saveAll(medication);
    		}
    	if(allergie != null) {
    		updatedMedicalRecord.get().setAllergies(allergie);
    		//allergiesRepository.saveAll(allergie);
    		}
    	return medicalRecordsRepository.save(updatedMedicalRecord.get());
        }
	
	public void deleteMedicalRecord(final String idDb) {
    	medicalRecordsRepository.deleteById(idDb);
    }
	
	public Iterable<MedicalRecord> searchChild(String address){
		Iterable<MedicalRecord> childAndFamily = medicalRecordsRepository.findAllByAddressAndBirthdate(address);
		return childAndFamily;
		
	}
}