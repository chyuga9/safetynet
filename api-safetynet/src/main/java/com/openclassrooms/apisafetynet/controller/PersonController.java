package com.openclassrooms.apisafetynet.controller;

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

import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.service.PersonService;



@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
    * Read - Get all persons
    * @return - An Iterable object of Person full filled
    * 
    * Surement à refaire ou supprimer car il ne demande pas juste des personnes
    */
    
    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }
    
    @GetMapping(value = "/firestation" , params ="stationNumber")
    public Iterable<Person> getAddressesByStationNumber(@RequestParam("stationNumber") String station) {
        return personService.getAddressesByStationNumber(station);
    }
    /**
	 * Create - Add a new person
	 * @param person An object person
	 * @return The person object saved
	 */
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
    /**
	 * Update - Update an existing person
	 * @param id - The id of the person to update
	 * @param person - The person object updated
	 * @return
	 * 
	 * A preciser dans la methode de l'autre classe qu'on peut modifier tous les champs sauf nom et prénom
	 
    @PutMapping("/person")
    public void updatePerson(Long id) {
        //personService.updatePerson(id);
        Optional<Person> e = personService.getPerson(id);
		if(e.isPresent()) {
			Person currentEmployee = e.get();
			
			String mail = employee.getMail();
			if(mail != null) {
				currentEmployee.setMail(mail);
			}
			String password = employee.getPassword();
			if(password != null) {
				currentEmployee.setPassword(password);;
			}
			employeeService.saveEmployee(currentEmployee);
			return currentEmployee;
		} else {
			return null;
		}
    }
    */
    /**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
    @DeleteMapping("/person/{id_bd}")
    public void deletePerson(@PathVariable("id_bd") String idDb) {
        personService.deletePerson(idDb);
    }

}