package com.openclassrooms.apisafetynet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.repository.FireStationsRepository;

import lombok.Data;

@Data
@Service
public class FireStationService {

    @Autowired
    private FireStationsRepository fireStationRepository;

    public Optional<FireStation> getFireStation(final Long id) {
        return fireStationRepository.findById(id);
    }

    public Iterable<FireStation> getFireStations() {
        return fireStationRepository.findAll();
    }

    public void deleteFireStation(final Long id) {
    	fireStationRepository.deleteById(id);
    }

    public FireStation saveFireStation(FireStation fireStation) {
    	FireStation savedFireStation = fireStationRepository.save(fireStation);
        return savedFireStation;
    }

}