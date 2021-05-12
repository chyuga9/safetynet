package com.openclassrooms.apisafetynet.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.apisafetynet.model.ChildAlertAndFamily;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.ChildAlert;
import com.openclassrooms.apisafetynet.projection.PhoneAlert;
import com.openclassrooms.apisafetynet.service.PersonService;



@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

	//---------- Méthodes de base --------


    @DeleteMapping("/person/{id_bd}")
    public void deletePerson(@PathVariable("id_bd") String idDb) {
        personService.deletePerson(idDb);
    }
    
    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }
    
    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }
    @PostMapping("/persons")
	public Iterable<Person> createPerson(@RequestBody Iterable<Person> persons) {
		return personService.savePersons(persons);
	}
    
    @PutMapping("/person/{id_db}")
    public Person updatePerson(@PathVariable("id_db") String idDb, @RequestBody Person person) {
        return personService.updatePerson(idDb,person);
    }
   
	//---------- Méthodes personalisées --------

   
    @GetMapping("/childAlert")
    public Iterable<ChildAlertAndFamily> getChildAlert(@RequestParam("address") String address){
    	return personService.getChildAlert(address);
    }
 
    @GetMapping("/phoneAlert")
    public Iterable<PhoneAlert> getPhones(@RequestParam("firestation") int station){
    	return personService.getPhones(station);
    }
}