package com.openclassrooms.apisafetynet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;

import lombok.Data;

@Data
@Service
public class PersonService {

    @Autowired
    private PersonsRepository personsRepository;

    public Optional<Person> getPerson(final String idDb) {
        return personsRepository.findById(idDb);
    }

    public Iterable<Person> getPersons() {
        return personsRepository.findAll();
    }
    // selon l'énoncé il faut un id qui combine le prénom et le nom
    public void deletePerson(final String idDb) {
    	personsRepository.deleteById(idDb);
    }
    
    public void updatePerson(final String idDb) {
    	personsRepository.findById(idDb);
    	
    }

    public Person savePerson(Person person) {
    	String id_bd = person.getFirstName() + "_" +person.getLastName();
    	person.setIdDb(id_bd);
        Person savedPerson= personsRepository.save(person);
        return savedPerson;
    }

}