package com.openclassrooms.apisafetynet.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.ApiSafetynetApplication;
import com.openclassrooms.apisafetynet.exceptions.UnfindablePersonException;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.PeopleAndCount;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.FireAlert;
import com.openclassrooms.apisafetynet.projection.People;
import com.openclassrooms.apisafetynet.repository.FireStationsRepository;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;

import javassist.NotFoundException;
import lombok.Data;

@Data
@Service
public class FireStationService {

    @Autowired
    private FireStationsRepository fireStationRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonsRepository personsRepository;
    
    private static final Logger logger = LogManager.getLogger(ApiSafetynetApplication.class);

	//---------- Méthodes de base --------

    public boolean deleteFireStation(int id) throws NotFoundException {
    	Optional<FireStation> deletedFireStation = fireStationRepository.findById(id);
    	if(deletedFireStation.isEmpty()) 
    		throw new NotFoundException("Aucune personne n'a été trouvée avec l'id " + id);
    	String address = deletedFireStation.get().getAddress();
    	fireStationRepository.delete(deletedFireStation.get());
        logger.info("Suppression de la caserne s'occupant de l'adresse " + address);
        return true;
    }
    
    public Optional<FireStation> getFireStation(final String address) throws NotFoundException {
        logger.info("Recherche la caserne s'occupant de l'adresse " + address);
        if(fireStationRepository.findByAddress(address).isEmpty()) 
    		throw new NotFoundException("Aucune firestation n'a été trouvée avec l'adresse " + address);
        return fireStationRepository.findByAddress(address);
    }

    public Iterable<FireStation> getFireStations() {
        logger.info("Recherche toutes les casernes");
    	return fireStationRepository.findAll();
    }
    
    public FireStation saveFireStation(FireStation fireStation) {
    	FireStation savedFireStation = fireStationRepository.save(fireStation);
    	personService.updateFireStationsPerson(savedFireStation.getAddress(), savedFireStation);
        logger.info("Enregistrement de la caserne " + fireStation.getStation() + " s'occupant de l'adresse " + savedFireStation.getAddress());
        return savedFireStation;
    }
 // Pour rentrer les données plus vite pour moi
    public Iterable<FireStation> saveFireStations(Iterable<FireStation> fireStations) {
    	for(FireStation fS : fireStations) {
    		fireStationRepository.save(fS);
        	personService.updateFireStationsPerson(fS.getAddress(), fS);
            logger.info("Enregistrement de la caserne " + fS.getStation() + " s'occupant de l'adresse " + fS.getAddress());
    	}
    	 return fireStations;
        }
    
    public FireStation updateFireStation(FireStation fireStation){
    	Optional<FireStation> updatedFireStation = fireStationRepository.findById(fireStation.getId());
    	updatedFireStation.get().setStation(fireStation.getStation());
        logger.info("Mise à jour de la caserne " + fireStation.getStation() + " qui s'occupera de l'adresse " + fireStation.getAddress());
    	return fireStationRepository.save(updatedFireStation.get());
    }
    
	//---------- Méthodes personalisées --------

    
    
	public PeopleAndCount getPeople(int station) {
		Iterable<People> ppl = personsRepository.findByFireStation_Station(station, People.class);
		Date now = Date.valueOf(LocalDate.now().minusYears(18));
		int nbAdult = personsRepository.countByFireStation_StationAndMedicalRecord_BirthdateBefore(station, now);
		int nbChild = personsRepository.countByFireStation_StationAndMedicalRecord_BirthdateAfter(station, now);
		PeopleAndCount pAC = new PeopleAndCount(ppl, nbAdult, nbChild);
		logger.info("Récupération des données de toutes les personnes couvertes par la caserne " + station + " ainsi que le décompte des adultes et enfants");
		return pAC;
		}
	
    public Iterable<FireAlert> getFireAlert(String address) {
		return personsRepository.findByAddress(address, FireAlert.class);		
			}
    
    public Iterable<FireAlert> getFloodAlert( int[] stations) {
    	ArrayList<FireAlert> list = new ArrayList<>();
		for(int station : stations) {
			Iterable<FireAlert> fireAlerts =  (personsRepository.findByFireStation_Station(station, FireAlert.class));
			for(FireAlert fireAlert : fireAlerts)
				list.add(fireAlert);
			}
		return list;
    }
  



/*

		*/
	
    // AJouter ensuite un décompte du nombre d'enfants et d'adultes
	//public List<FireStation> getAddressesByStationNumber(int station) {
		
		//List<FireStation> address = fireStationRepository.findAllByStationNumber(station);
		//return address;
	//}
    /*
     *  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
    return repository.findById(id).map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

     */
     
}