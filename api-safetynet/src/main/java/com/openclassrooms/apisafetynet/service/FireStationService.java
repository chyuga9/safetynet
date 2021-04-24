package com.openclassrooms.apisafetynet.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.FireStationsRepository;

import lombok.Data;

@Data
@Service
public class FireStationService<U> {

    @Autowired
    private FireStationsRepository fireStationRepository;

    public Optional<FireStation> getFireStation(final String address) {
        return fireStationRepository.findById(address);
    }

    public Iterable<FireStation> getFireStations() {
        return fireStationRepository.findAll();
    }

    public void deleteFireStation(final FireStation fireStation) {
    	Optional<FireStation> deletedFireStation = fireStationRepository.findById(fireStation.getAddress());
    	fireStationRepository.delete(deletedFireStation.get());
    }

    
    public FireStation saveFireStation(FireStation fireStation) {
    	FireStation savedFireStation = fireStationRepository.save(fireStation);
        return savedFireStation;
    }
    
    public FireStation updateFireStation(FireStation fireStation){
    	/*Optional<FireStation> updatedFireStation = fireStationRepository.findById(address).map(firestation ->{firestation.setStation(station); });
    	updatedFireStation.
    	return updatedFireStation;
    	*/
    	Optional<FireStation> updatedFireStation = fireStationRepository.findById(fireStation.getAddress());
    	updatedFireStation.get().setStation(fireStation.getStation());
    	return fireStationRepository.save(updatedFireStation.get());
    	//return fireStationRepository.findById(address).map((Function<? super FireStation, ? extends U>) firestation ->{firestation.setStation(fireStation.getStation()); })
    	 //.orElseGet(() -> {    	        fireStation.setAddress(address);    	        fireStationRepository.save(fireStation);});
    	
    }
    // AJouter ensuite un décompte du nombre d'enfants et d'adultes
	public List<FireStation> getAddressesByStationNumber(int station) {
		
		List<FireStation> address = fireStationRepository.findAllByStationNumber(station);
		return address;
	}
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