package com.openclassrooms.apisafetynet.controller;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
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
import com.openclassrooms.apisafetynet.model.ChildAlertAndFamily;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.ChildAlert;
import com.openclassrooms.apisafetynet.projection.Email;
import com.openclassrooms.apisafetynet.projection.PersonInfo;
import com.openclassrooms.apisafetynet.projection.PhoneAlert;
import com.openclassrooms.apisafetynet.service.PersonService;



@RestController
public class PersonController {

    @Autowired
    private PersonService personService;
    
    
    private ResponseEntity<Void> response;


	//---------- Méthodes de base --------


    @DeleteMapping("/person/{id_bd}")
    public ResponseEntity<String> deletePerson(@PathVariable("id_bd") String idDb) {
    	// Possibilité 1 de gérer une exception
    	Optional<Person> person = personService.getPerson(idDb);
    	if(person == null ) throw new UnfindablePersonException("La personne avec l'id " + idDb + " est INTROUVABLE.");
        personService.deletePerson(idDb);
        return ResponseEntity.ok().body("Person deleted");
    }
    
    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> getPersons() {
        return ResponseEntity.ok().body(personService.getPersons());
        
    }
    /*
    @PostMapping("/person")
    public ResponseEntity<Void> createPerson(@RequestBody Person person) {
        Person p = personService.savePerson(person);
       // Possibilité 2 de gérer une exception
        if (p == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id_Db}")
                .buildAndExpand(person.getIdDb())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PostMapping("/persons")
	public Iterable<Person> createPerson(@RequestBody Iterable<Person> persons) {
		return personService.savePersons(persons);
	}
    */
    // trouver quelle response entity renvoyer pour plusieurs enregsiremens
    
    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person p = personService.savePerson(person);
        /*//Fonctionne mais je veux tester autre chose
        
                return ResponseEntity.created(location).build();
                */
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id_Db}")
                .buildAndExpand(person.getIdDb())
                .toUri();
        return  ResponseEntity.created(location)
        		.header("jhyuhuih","uihyug") // juste pour savoir ce que ça fait
        		.body(p);
    }
    @PostMapping("/persons")
    public ResponseEntity<Void> createPersons(@RequestBody Iterable<Person> persons) {
    	Iterable<Person> p = personService.savePersons(persons);
        if (p == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/packdeperson").build(false).toUri();
        
        return ResponseEntity.created(location).build();
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
   
    @GetMapping(value = "/personInfo")
    public Iterable<PersonInfo> getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
    	return personService.getPersonInfo(firstName, lastName);
    }
    
    @GetMapping(value = "/communityEmail")
    public Iterable<Email> getEmail(@RequestParam("city") String city){
    	return personService.getEmail(city);
    }
 /*
  * 

 
    */
}