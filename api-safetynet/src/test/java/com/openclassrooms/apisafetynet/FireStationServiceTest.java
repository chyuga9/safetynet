package com.openclassrooms.apisafetynet;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.apisafetynet.controller.FireStationController;
import com.openclassrooms.apisafetynet.exceptions.UnfindablePersonException;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.PeopleAndCount;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.FireStationsRepository;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;
import com.openclassrooms.apisafetynet.service.FireStationService;
import com.openclassrooms.apisafetynet.service.PersonService;
import com.openclassrooms.apisafetynet.projection.FireAlert;
import com.openclassrooms.apisafetynet.projection.People;

import javassist.NotFoundException;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

// ---- ------ !!!!!  Peut thrown une IllegalStateException : failed context loaded parce que wampserver n'a pas été démarré

@SpringBootTest
public class FireStationServiceTest {
	
	@Autowired
	private FireStationService fsS;
	
	@MockBean
	private FireStationsRepository fsR;
	
	@MockBean
	private PersonsRepository pR;
	
	static private FireStation fs;
	
	@MockBean
	private MedicalRecord mr;
	
	@MockBean
	private Person person;
	
	@MockBean
	PersonService ps;
	
	@MockBean
	private FireAlert fA;
	
	private Optional<FireStation> ofs;
	
	//private Optional<String> optionalString;

	private ArrayList<FireStation> listFireStations;
	
	private ArrayList<People> ppl;
	
	private ArrayList<FireAlert> lfA;
	
	private ArrayList<Person> listPerson;
		
	int x; 
	private PeopleAndCount pAC;
	
	int[] u = new int[2];
	
	@BeforeEach
	public void setUp() {
		fs = new FireStation();
		fs.setId(2);
		//children = new ArrayList<>();
		//listPerson = new ArrayList<>();
		x = (int) (Math.random()*((10-1)+1))+1; // nombre entier au hasard - double x = (int)(Math.random()*((max-min)+1))+min;
		u[0] = x;
		u[1] = x + 3;
		
	}
	// Mockito.anyString() + "_" + Mockito.anyString()
	
	@Test
	public void equalsContract() {
	    EqualsVerifier.simple().suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.ALL_FIELDS_SHOULD_BE_USED)
	            .forClass(FireStationService.class)
	            .verify();
	}
	
	@DisplayName("La valeur \"true\" nous est renvoyé lorsque la firestation avec l'id donnée a été supprimée")
	@Test
	public void givenAnId_whenWeDeleteAFireStationWithThisId_thenItIsDeletedFromTheDatabase() throws NotFoundException {
		when(fsR.findById(fs.getId()))
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				ofs.of(fs));

		assertThat(fsS.deleteFireStation(fs.getId())).isTrue();
	}
	
	@DisplayName("Envoie une erreur lorsque l'id n'est pas bon")
	@Test
	public void givenAWrongId_whenWeWantToDeleteAFireStationWithThisId_thenAnErrorIsThrown() {
		assertThrows(NotFoundException.class, ()-> fsS.deleteFireStation(Mockito.anyInt()));
	}
	
	@DisplayName("Une firestation nous est retournée")
	@Test
	public void givenAnAddress_whenWeGetAFireStationWithThisAddress_thenItIsDisplayed() throws NotFoundException {
		when(fsR.findByAddress(fs.getAddress()))
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				ofs.of(fs));
		assertThat(fsS.getFireStation(fs.getAddress()))
		.contains(fs);
	}
	
	@DisplayName("Envoie une erreur lorsque l'id n'est pas bon")
	@Test
	public void givenAWrongIdDb_whenWeWantToDisplayedAPersonWithThisIdDb_thenAnErrorIsThrown() {
		assertThrows(NotFoundException.class, ()-> fsS.getFireStation(Mockito.anyString()));
	}
	
	@Test
	public void givenWeWantToCheckAllFireStationsInTheDatabase_WhenWegetAllTheFireStations_thenAllTheFireStationsAreDisplayed() throws UnfindablePersonException {
		listFireStations = new ArrayList<>();
		listFireStations.add(fs);
		when(fsR.findAll())
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				listFireStations);
		assertThat(fsS.getFireStations())
		.containsAll(listFireStations);
	}
	
	@Test
	public void givenAFireStation_whenWeSaveIt_thenItIsDisplayed() {
		when(fsR.save(fs)).thenReturn(fs);
		assertThat(fsS.saveFireStation(fs).equals(fs));
	}
	
	@Test
	public void givenMultipleFireStations_whenWeSaveThem_thenTheyAreReturned() {
		listFireStations = new ArrayList<>();
		FireStation fs2 = new FireStation();
		listPerson = new ArrayList<>();
		when(fsR.save(fs)).thenReturn(fs);
		when(fsR.save(fs2)).thenReturn(fs2);

		listFireStations.add(fs);
		
		listFireStations.add(fs2);

		person.setFireStation(fs);
		listPerson.add(person);
		when(ps.updateFireStationsPerson(fs.getAddress(),fs)).thenReturn(listPerson);
		assertThat(fsS.saveFireStations(listFireStations).equals(listFireStations));
	}
	
	@Test
	public void givenANewInformationForAFireStation_WhenWePutItIntoTheDatabase_ThenTheUpdatedFireStationIsDisplayedWithTheNewInformation() {
		when(fsR.findById(fs.getId())).thenReturn(ofs.of(fs));
		fs.setAddress("rue 123");
		when(fsR.save(fs)).thenReturn(fs);
		assertThat(fsS.updateFireStation(fs).getAddress().equals("rue 123"));
	}
	
	@Test
	public void givenAStation_whenWeLookForPeopleaAndTheirNumber_thenReturnPeopleAndTheCountOfAdultsAndChildren() {
		Date now = Date.valueOf(LocalDate.now().minusYears(18));
		when(pR.findByFireStation_Station(x, People.class)).thenReturn(ppl);
		when(pR.countByFireStation_StationAndMedicalRecord_BirthdateBefore(x, now)).thenReturn(3);
		when(pR.countByFireStation_StationAndMedicalRecord_BirthdateAfter(x, now)).thenReturn(4);
		pAC = new PeopleAndCount(ppl, 3, 4);

		assertThat(fsS.getPeople(x))
		.isEqualTo(pAC);
	}
	
	@Test
	public void givenAnAddress_whenWeAskForFireAlert_thenTheFireAlertIsDisplayed() {
		when(pR.findByAddress("ouioui", FireAlert.class)).thenReturn(lfA);
		assertThat(fsS.getFireAlert("ouioui")).isEqualTo(lfA);
	}
	@Test
	public void givenAnArrayOfStations_whenWeAskForFireAlert_thenAListOfFireAlertsAreDisplayed() {
		lfA = new ArrayList<>();
		lfA.add(fA);
		when(pR.findByFireStation_Station(x, FireAlert.class)).thenReturn(lfA);
		assertThat(fsS.getFloodAlert(u).equals(lfA));
	
}
}
