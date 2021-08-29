package com.openclassrooms.apisafetynet.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.ApiSafetynetApplication;
import com.openclassrooms.apisafetynet.exceptions.UnfindablePersonException;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.MedicalRecordsRepository;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;

import javassist.NotFoundException;
import lombok.Data;

@Data
@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;
    
    @Autowired
    private PersonService personService;
    
    private static final Logger logger = LogManager.getLogger(ApiSafetynetApplication.class);

    public boolean deleteMedicalRecord(final String idDb) throws NotFoundException {
		if(medicalRecordsRepository.findByIdBd(idDb).isEmpty()) 
    		throw new NotFoundException("Aucun medical record n'a été trouvé avec l'id " + idDb);
    	medicalRecordsRepository.deleteByIdBd(idDb);
    	return true;
    }
  
    public Optional<MedicalRecord> getMedicalRecord(final String idDb) throws NotFoundException {
    	if(medicalRecordsRepository.findByIdBd(idDb).isEmpty()) 
    		throw new NotFoundException("Aucun medical record n'a été trouvé avec l'id " + idDb);
        return medicalRecordsRepository.findByIdBd(idDb);
    }

    public Iterable<MedicalRecord> getMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }
    
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) throws UnfindablePersonException {
		
			String idBd = medicalRecord.getFirstName() + "_" + medicalRecord.getLastName();
			medicalRecord.setIdBd(idBd);
			/*
			String pers_id =medicalRecordsRepository.findByIdDb(medicalRecord.getIdBd());
			if(!pers_id.isEmpty()) {
				medicalRecordsRepository.createRowInJointTable(idBd, pers_id);
			};    	*/
			MedicalRecord savedMedicalRecord= medicalRecordsRepository.save(medicalRecord);
			try{personService.updateMedicalRecordsPerson(idBd,savedMedicalRecord);}
			catch(UnfindablePersonException e){
				throw new UnfindablePersonException("Le medical record n'a été rattaché à personne car l'identifiant est inconnu");}
			finally {return savedMedicalRecord;}
		    
		    
		
		}
    // Pour rentrer les données plus vite pour moi
    public Iterable<MedicalRecord> createMedicalRecords(Iterable<MedicalRecord> medicalRecords) throws UnfindablePersonException {
    	try {
			for(MedicalRecord medRec : medicalRecords) {
			String idBd = medRec.getFirstName() + "_" +  medRec.getLastName();
			medRec.setIdBd(idBd);
			medicalRecordsRepository.save(medRec);
			personService.updateMedicalRecordsPerson(idBd,medRec);
			logger.info("Mise à jour réussie du dossier médical de " + medRec.getFirstName() + " " + medRec.getLastName());
			}
		} catch(UnfindablePersonException e){throw new UnfindablePersonException("Le medical record n'a été rattaché à personne car l'identifiant est inconnu");}
    	finally {
    	 return medicalRecords;
    	}
        }
    
	public MedicalRecord updateMedicalRecord(String idDb, MedicalRecord medicalRecord) {
		Optional<MedicalRecord> updatedMedicalRecord = medicalRecordsRepository.findByIdBd(idDb);
    	String medication = medicalRecord.getMedications();
    	String allergie = medicalRecord.getAllergies();
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
	
	}
