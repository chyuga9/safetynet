package com.openclassrooms.apisafetynet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.model.FireStation;
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
    
    public Person updatePerson(String idDb, Person person){
    	
    	Optional<Person> updatedPerson = personsRepository.findById(idDb);
    	String address = person.getAddress();
    	Date birthDate = person.getBirthdate();
    	String city = person.getCity();
    	String email = person.getEmail();
    	String phone = person.getPhone();
    	String zip = person.getZip();
    	if(address != null) {
    	updatedPerson.get().setAddress(address);}
    	if(birthDate != null) {
    	updatedPerson.get().setBirthdate(birthDate);}
    	if(city != null) {
    	updatedPerson.get().setCity(city);}
    	if(email != null) {
    	updatedPerson.get().setEmail(email);}
    	if(phone != null) {
    		updatedPerson.get().setPhone(phone);}
    	if(zip != null ) {
    		updatedPerson.get().setZip(zip);}
    	return personsRepository.save(updatedPerson.get());
        }

    public Person savePerson(Person person) {
    	
    	String id_bd = person.getFirstName() + "_" +person.getLastName();
    	person.setIdDb(id_bd);
    	/*String birthdateToString = String.valueOf(person.getBirthdate());
    	SimpleDateFormat formater = new SimpleDateFormat("DD/MM/YYYY");
    	Date birthdate;
		try {
	        SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");
			birthdateToString = formatter.format(formater.parse(birthdateToString));
			birthdate = formater.parse(birthdateToString);
	        person.setBirthdate(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
        Person savedPerson = personsRepository.save(person);
        return savedPerson;
    }

}