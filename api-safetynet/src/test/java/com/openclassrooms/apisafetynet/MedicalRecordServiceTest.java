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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.apisafetynet.exceptions.UnfindablePersonException;
import com.openclassrooms.apisafetynet.model.ChildAlertAndFamily;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.ChildAlert;
import com.openclassrooms.apisafetynet.repository.MedicalRecordsRepository;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;
import com.openclassrooms.apisafetynet.service.MedicalRecordService;
import com.openclassrooms.apisafetynet.service.PersonService;

import javassist.NotFoundException;

//---- ------ !!!!!  Peut thrown une IllegalStateException : failed context loaded parce que wampserver n'a pas été démarré


@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {
	
	@InjectMocks
	private MedicalRecordService mrs;

	@Mock
	private MedicalRecordsRepository mrr;
	
	@Mock
	private PersonService ps;
	
	static private Person person;
	
	private MedicalRecord mr;
	/*
	@MockBean
	private FireStation fs;
	*/
	
	//static private Optional<Person> omr;
	
	
	private ArrayList<MedicalRecord> listMedicalRecord;

	
	@BeforeEach
	public void setUp() {
		mr = new MedicalRecord("prenom", "nom", "prenom_nom", null, null, null);		
	}	

	@DisplayName("La valeur \"true\" nous est renvoyé lorsque le medical record avec l'idDb donné a été supprimé")
	@Test
	public void givenAnIdDb_whenWeDeleteAPersonWithThisIdDb_thenHeIsDeletedFromTheDatabase() throws NotFoundException {
		when(mrr.findByIdBd(mr.getIdBd()))
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				Optional.of(mr));
		assertThat(mrs.deleteMedicalRecord(mr.getIdBd())).isTrue();
	}
	
	@DisplayName("Envoie une erreur lorsque l'idBd n'est pas bon")
	@Test
	public void givenAWrongIdDb_whenWeWantToDeleteAPersonWithThisIdDb_thenAnErrorIsThrown() {
		assertThrows(NotFoundException.class, ()-> mrs.deleteMedicalRecord(Mockito.anyString()));
	}
	
	@DisplayName("Un medical record nous est retourné")
	@Test
	public void givenAnIdDb_whenWeGetAMedicalRecordWithThisIdDb_thenItIsDisplayed() throws NotFoundException {
		when(mrr.findByIdBd(mr.getIdBd()))
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				Optional.of(mr));
		assertThat(mrs.getMedicalRecord(mr.getIdBd()))
		.contains(mr);
	}
	
	@DisplayName("Envoie une erreur lorsque l'idDb n'est pas bon")
	@Test
	public void givenAWrongIdDb_whenWeWantToDisplayedAMedicalRecordWithThisIdDb_thenAnErrorIsThrown() {
		assertThrows(NotFoundException.class, ()-> mrs.getMedicalRecord(Mockito.anyString()));
	}
	
	@Test
	public void givenWeWantToCheckAllMedicalRecordsInTheDatabase_WhenWegetAllTheMedicalRecords_thenAllTheMedicalRecordsAreDisplayed() throws UnfindablePersonException {
		listMedicalRecord = new ArrayList<>();
		listMedicalRecord.add(mr);
		when(mrr.findAll())
		.thenReturn(
				//Optional.of(Mockito.any(Person.class))
				listMedicalRecord);
		assertThat(mrs.getMedicalRecords())
		.containsAll(listMedicalRecord);
	}
	
	@Test
	public void givenAMedicalRecord_whenWeSaveIt_thenItIsDisplayed() throws UnfindablePersonException {
		when(mrr.save(mr)).thenReturn(mr);
		when(ps.updateMedicalRecordsPerson("uihuh", mr)).thenReturn(person);
		assertThat(mrs.createMedicalRecord(mr).equals(mr));
	}
	
	@Test
	public void givenMultipleMedicalRecords_whenWeSaveThem_thenTheyAreReturned() throws UnfindablePersonException {
		listMedicalRecord = new ArrayList<MedicalRecord>();
		MedicalRecord mr2 = new MedicalRecord();
		person = new Person();
		Person person2 = new Person();
		when(mrr.save(mr)).thenReturn(mr);
		when(mrr.save(mr2)).thenReturn(mr2);
		listMedicalRecord.add(mr);
		listMedicalRecord.add(mr2);
		person.setMedicalRecord(mr);
		person2.setMedicalRecord(mr2);
		when(ps.updateMedicalRecordsPerson("uihuh", mr)).thenReturn(person);
		when(ps.updateMedicalRecordsPerson("uihuh", mr2)).thenReturn(person2);
		assertThat(mrs.createMedicalRecords(listMedicalRecord).equals(listMedicalRecord));
	}
	
	@Test
	public void givenANewInformationForAMedicalRecord_whenWePutItIntoTheDatabase_ThenTheUpdatedMedicalRecordIsDisplayedWithTheNewInformation() {
		Date date = Date.valueOf(LocalDate.now());
		MedicalRecord mr2 = new MedicalRecord("prenom", "nom", "prenom_nom", date, null, null);		
		when(mrr.findByIdBd(mr.getIdBd())).thenReturn(Optional.of(mr));
		when(mrr.save(mr2)).thenReturn(mr2);
		assertThat(mrs.updateMedicalRecord(mr.getIdBd(),mr2).getBirthdate().equals(date));
	}
	
}
