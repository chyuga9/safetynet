package com.openclassrooms.apisafetynet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.repository.MedicalRecordsRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;

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

}