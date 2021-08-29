package com.openclassrooms.apisafetynet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;

import com.openclassrooms.apisafetynet.exceptions.UnfindablePersonException;
import com.openclassrooms.apisafetynet.model.ChildAlertAndFamily;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.ChildAlert;
import com.openclassrooms.apisafetynet.projection.PersonInfo;
import com.openclassrooms.apisafetynet.projection.PhoneAlert;
import com.openclassrooms.apisafetynet.repository.FireStationsRepository;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;
import com.openclassrooms.apisafetynet.service.PersonService;

//---- ------ !!!!!  Peut thrown une IllegalStateException : failed context loaded parce que wampserver n'a pas été démarré
// --- https://www.toptal.com/java/a-guide-to-everyday-mockito

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService ps;
	

	
	@Mock
	private PersonsRepository pr;
	
	@Mock
	private FireStationsRepository fsr;
	
	static private Person person;
	
	@Mock
	private MedicalRecord mr;
	
	@Mock
	private FireStation fs;
	
	@Mock
	private ChildAlert childAlert;
	
	private Optional<Person> pers;
	
	//private Optional<String> optionalString;

	
	private ArrayList<Person> listPerson;
	
	private ArrayList<ChildAlert> children;
	
	@Mock
	private ArrayList<PhoneAlert> phoneAlert;
	
	@Mock
	private ArrayList<PersonInfo> personInfo;
	
	@BeforeEach
	public void setUp() {
		person = new Person("prenom", "nom", null, null, null, null, null);
		person.setIdDb("prenom_nom");
		children = new ArrayList<>();
		listPerson = new ArrayList<>();
		
	}
	// Mockito.anyString() + "_" + Mockito.anyString()
	
	@DisplayName("La valeur \"true\" nous est renvoyé lorsque la personne avec l'idDb donnée a été supprimée")
	@Test
	public void givenAnIdDb_whenWeDeleteAPersonWithThisIdDb_thenHeIsDeletedFromTheDatabase() throws UnfindablePersonException {
		when(pr.findByIdDb(person.getIdDb()))
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				pers.of(person));

		assertThat(ps.deletePerson(person.getIdDb())).isTrue();
	}
	
	@DisplayName("Envoie une erreur lorsque l'idDb n'est pas bon")
	@Test
	public void givenAWrongIdDb_whenWeWantToDeleteAPersonWithThisIdDb_thenAnErrorIsThrown() {
		assertThrows(UnfindablePersonException.class, ()-> ps.deletePerson(Mockito.anyString()));
	}
	
	@DisplayName("Une personne nous est retournée")
	@Test
	public void givenAnIdDb_whenWeGetAPersonWithThisIdDb_thenHeIsDisplayed() throws UnfindablePersonException {
		when(pr.findByIdDb(person.getIdDb()))
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				pers.of(person));
		assertThat(ps.getPerson(person.getIdDb()))
		.contains(person);
	}
	
	@DisplayName("Envoie une erreur lorsque l'idDb n'est pas bon")
	@Test
	public void givenAWrongIdDb_whenWeWantToDisplayedAPersonWithThisIdDb_thenAnErrorIsThrown() {
		assertThrows(UnfindablePersonException.class, ()-> ps.getPerson(Mockito.anyString()));
	}
	
	@Test
	public void givenWeWantToCheckAllPersonsInTheDatabase_WhenWegetAllThePersons_thenAllThePersonsAreDisplayed() throws UnfindablePersonException {
		ArrayList<Person> listPersons = new ArrayList<>();
		listPersons.add(person);
		when(pr.findAll())
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				listPersons);
		assertThat(ps.getPersons())
		.containsAll(listPersons);
	}
	
	@Test
	public void givenAPerson_whenWeSaveHim_thenHeIsDisplayed() {
		when(pr.save(person)).thenReturn(person);
		when(fsr.findByAddress(person.getAddress())).thenReturn(Optional.of(fs));
		
		assertThat(ps.savePerson(person).equals(person));
//		when(mrr.save(mr)).thenReturn(mr);
//		when(ps.updateMedicalRecordsPerson("uihuh", mr)).thenReturn(person);
//		assertThat(mrs.createMedicalRecord(mr).equals(mr));
	}
	
	@Test
	public void givenMultiplePersons_whenWeSaveThem_thenTheyAreDisplayed() {
		listPerson = new ArrayList<Person>();
		Person autrePerson = new Person();
		listPerson.add(person);
		listPerson.add(autrePerson);		
		when(fsr.findByAddress(person.getAddress())).thenReturn(Optional.of(fs));
		when(fsr.findByAddress(autrePerson.getAddress())).thenReturn(Optional.of(fs));
		when(pr.save(person)).thenReturn(person);
		when(pr.save(autrePerson)).thenReturn(autrePerson);
		assertThat(ps.savePersons(listPerson).equals(listPerson));
//		when(mrr.save(mr)).thenReturn(mr);
//		when(ps.updateMedicalRecordsPerson("uihuh", mr)).thenReturn(person);
//		assertThat(mrs.createMedicalRecord(mr).equals(mr));
	}
	
	@Test
	public void givenANewInformationForAPerson_WhenWePutItIntoTheDatabase_ThenTheUpdatedPersonIsDisplayedWithTheNewInformation() {
		when(pr.findByIdDb(person.getIdDb())).thenReturn(pers.of(person));
		person.setAddress("rue 123");
		when(pr.save(person)).thenReturn(person);
		assertThat(ps.updatePerson(person.getIdDb(),person).getAddress().equals("rue 123"));
	}
	
	@Test
	public void givenAnAdress_whenWeLookForInformation_ThenReturnInfosAboutChildrenAndTheirFamily() {
		ChildAlertAndFamily cAAF = new ChildAlertAndFamily();
		ArrayList<ChildAlertAndFamily> listCAAF = new ArrayList();
		Date now = Date.valueOf(LocalDate.now().minusYears(18));
		Person person2 = person;
		Person person3 = person;
		children.add(childAlert);
		
		//when(pr.findByAddressAndMedicalRecord_BirthdateAfter(Mockito.anyString(), Mockito.any(Date.class))).thenReturn(children);
		when(pr.findByAddressAndMedicalRecord_BirthdateAfter("nojojhuyig", now)).thenReturn(children);
		cAAF.setChildAlert(childAlert);
		listPerson.add(person);
		listPerson.add(person2);
		listPerson.add(person3);
		//when(pr.findByLastNameAndAddressAndFirstNameNot(Optional.of(Mockito.anyString()),Mockito.anyString(), Optional.of(Mockito.anyString()))).thenReturn(iterablePers);
		when(pr.findByLastNameAndAddressAndFirstNameNot(Optional.empty(),"nojojhuyig", Optional.empty())).thenReturn(listPerson);
		//cAAF.getFamilyMembers().add(person.getLastName().concat(" ").concat(person.getFirstName()));
		assertThat(ps.getChildAlert("nojojhuyig"))
		.containsAll(listCAAF);
	}
	
	@Test
	public void givenAnIdDb_whenWeEnteraMedicalRecord_thenTheMedicalRecordOfThePersonIsUpdatedWithNewInfos() throws UnfindablePersonException {
		//when(pr.findByIdDb(person.getIdDb())).thenReturn(pers.of(person));
		when(pr.findByIdDb(person.getIdDb())).thenReturn(pers.of(person));
		when(pr.save(person)).thenReturn(person);
		assertThat(ps.updateMedicalRecordsPerson(person.getIdDb(), mr).getMedicalRecord().equals(mr));
	}

	@Test
	public void givenAnAddress_whenWeEnterFireStation_thenAllThePersonWhoLivesAtTheAddressHasBeenUpdatedWithTheNewFireStation() {
		//when(pr.findByIdDb(person.getIdDb())).thenReturn(pers.of(person));
		Person person2 = new Person();
		when(pr.findByAddress("poi",Person.class)).thenReturn(listPerson);
		person.setFireStation(fs);
		person2.setFireStation(fs);
		listPerson.add(person);
		listPerson.add(person2);
		when(pr.save(person)).thenReturn(person);
		when(pr.save(person2)).thenReturn(person2);
		assertThat(ps.updateFireStationsPerson("poi", fs).equals(listPerson));
	}
	
	@Test
	public void givenAFireStationStation_whenWeEnterFireStation_thenAllThePhonesPersonCaringByThisFireStationAreReturned() {
		//when(pr.findByIdDb(person.getIdDb())).thenReturn(pers.of(person));
		when(pr.findByFireStation_Station(5,PhoneAlert.class)).thenReturn(phoneAlert);
		assertThat(ps.getPhones(5).equals(phoneAlert));
	}
	
	@Test
	public void givenWeWantPersonsInformations_whenWeEnterFirstNameAndLastNAme_thenAllThePersonsInformationsAreReturned() {
		//when(pr.findByIdDb(person.getIdDb())).thenReturn(pers.of(person));
		when(pr.findByLastName("poiibvln")).thenReturn(personInfo);
		assertThat(ps.getPersonInfo("oihjoih","poiibvln").equals(personInfo));
	}
}
