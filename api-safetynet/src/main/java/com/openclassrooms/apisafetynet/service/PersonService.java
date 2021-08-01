package com.openclassrooms.apisafetynet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.apisafetynet.ApiSafetynetApplication;
import com.openclassrooms.apisafetynet.conversion.PersonConversion;
import com.openclassrooms.apisafetynet.dto.PersonDto;
import com.openclassrooms.apisafetynet.model.ChildAlertAndFamily;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.ChildAlert;
import com.openclassrooms.apisafetynet.projection.Email;
import com.openclassrooms.apisafetynet.projection.PersonInfo;
import com.openclassrooms.apisafetynet.projection.PhoneAlert;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;

import lombok.Data;

@Data
@Transactional // Besoin de l'ajouter car probleme lorsque j'essayais delete une personne
@Service
public class PersonService {

    @Autowired
    private PersonsRepository personsRepository;
    
    //@PersistenceContext
    //private EntityManager manager;
    
    private static final Logger logger = LogManager.getLogger(ApiSafetynetApplication.class);

    
    
	//---------- Méthodes de base --------

    public void deletePerson(final String idDb) {
    	System.out.println("Est ce que ca marche ?");
    	Optional<Person> p = personsRepository.deleteByIdDb(idDb);
        logger.info(p.get().getFirstName() + p.get().getLastName() + " a été supprimé de la base de données");
    }
    
    public Optional<Person> getPerson(final String idDb) {
        logger.info("Recherche de la personne avec l'identifiant " + idDb);
        return personsRepository.findByIdDb(idDb);
    }

    public Iterable<Person> getPersons() {
        logger.info("Recherche toutes les personnes");
        return personsRepository.findAll();
    }
    
    public Person savePerson(Person person) {	
    	String id_bd = person.getFirstName() + "_" + person.getLastName();
    	person.setIdDb(id_bd);
        Person savedPerson = personsRepository.save(person);
        logger.info("Enregistrement de " + person.getFirstName() + " " + person.getLastName() + " dans la base de données ");
        return savedPerson;
    }
    
    // Pour rentrer les données plus vite pour moi
    public Iterable<Person> savePersons(Iterable<Person> persons) {
    	for(Person pers : persons) {
    	savePerson(pers);
    		/*
    	 * String id_bd = pers.getFirstName() + "_" +  pers.getLastName();
    	 
    	pers.setIdDb(id_bd);
    	personsRepository.save(pers);
        logger.info("Enregistrement de " + pers.getFirstName() + " " + pers.getLastName() + " dans la base de données ");
    	*/
    	}
    	 return persons;
        }
    
    public Person updatePerson(String idDb, Person person){
    	Optional<Person> updatedPerson = personsRepository.findByIdDb(idDb);
    	String address = person.getAddress();
    	//Date birthDate = person.getBirthdate();
    	String city = person.getCity();
    	String email = person.getEmail();
    	String phone = person.getPhone();
    	String zip = person.getZip();
    	if(address != null) {updatedPerson.get().setAddress(address);}
    	if(city != null) {updatedPerson.get().setCity(city);}
    	if(email != null) {updatedPerson.get().setEmail(email);}
    	if(phone != null) {updatedPerson.get().setPhone(phone);}
    	if(zip != null ) {updatedPerson.get().setZip(zip);}
        logger.info("Mise à jour réussie du dossier de " + updatedPerson.get().getFirstName() + " " + updatedPerson.get().getLastName());
    	return personsRepository.save(updatedPerson.get());
        }


	//---------- Méthodes personalisées --------

    public Iterable<ChildAlertAndFamily> getChildAlert(String address) {
		Date now = Date.valueOf(LocalDate.now().minusYears(18));
		Iterable<ChildAlert> children =  personsRepository.findByAddressAndMedicalRecord_BirthdateAfter(address, now);
		ChildAlertAndFamily cAAF;
		ArrayList<ChildAlertAndFamily> listCAAF = new ArrayList();
	
		for(ChildAlert child : children) {
			cAAF = new ChildAlertAndFamily();
			cAAF.getFamilyMembers().clear();
			cAAF.setChildAlert(child);
			Iterable<Person> familyMembers = personsRepository.findByLastNameAndAddressAndFirstNameNot(child.getLastName(), address, child.getFirstName());
			for(Person familyMember : familyMembers) {
				cAAF.getFamilyMembers().add(familyMember.getLastName().concat(" ").concat(familyMember.getFirstName()));
			}
			listCAAF.add(cAAF);
		}
		
		return listCAAF;
	}
    
    public Iterable<PhoneAlert> getPhones(int station){
		return personsRepository.findByFireStation_Station(station, PhoneAlert.class);
	}

	public Iterable<PersonInfo> getPersonInfo(String firstName, String lastName) {
		return personsRepository.findByLastName(lastName);
	}
    
	public Iterable<Email> getEmail(String city) {
		return personsRepository.findByCity(city);
	}
	
	//--------- Je ne sais pas pour le reste
	/*
public Iterable<Person> getAddressesByStationNumber(String station) {
		
		Iterable<Person> address = personsRepository.findAllByStationNumber(station);
		return address;
		
	Iterable<Person> persons = manager.createNamedQuery("Persons", Person.class).getResultList();
	return persons;
	}*/
	public Person updateMedicalRecordsPerson(String idBd, MedicalRecord medicalRecord) {
		Optional<Person> person = personsRepository.findByIdDb(idBd);
    	person.get().setMedicalRecord(medicalRecord);
    	return personsRepository.save(person.get());
	}
	public Iterable<Person> updateFireStationsPerson(String address, FireStation fireStation) {
		Iterable<Person> persons = personsRepository.findByAddress(address,Person.class);
    	for(Person pers : persons) {
    		pers.setFireStation(fireStation);
        	personsRepository.save(pers);
    	}
    		return persons;
	}



}
	

/*
	
	public Iterable<ChildAlert> getChildrenList(String address) {
			Date now = Date.valueOf(LocalDate.now().minusYears(18));
			Iterable<ChildAlert> children =  personsRepository.findByAddressAndMedicalRecord_BirthdateAfter(address, now);
			for(ChildAlert child : children) {
				//Iterable<OtherFamilyMembers> familyMember = personsRepository.findByLastNameAndFirstNameNot(child.getLastName(),child.getFirstName());
				getOtherFamilyMembers(child.getLastName(),child.getFirstName());
			}
			return children;
	}
	
	public Iterable<OtherFamilyMembers> getOtherFamilyMembers(Optional<String> lastName, Optional<String> firstName){
		return personsRepository.findByLastNameAndFirstNameNot(lastName,firstName);
	}
	
	public Optional<PersonDto> test(int id) {
		Optional<PersonDto> pers = new Optional<PersonDto>();
		PersonConversion pc = new PersonConversion(); 
		pers = pc.personToDto(personsRepository.findById(id).get());
		
	}
	*/


	

