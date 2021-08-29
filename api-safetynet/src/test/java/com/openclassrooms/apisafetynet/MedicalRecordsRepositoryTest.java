package com.openclassrooms.apisafetynet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.MedicalRecordsRepository;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;

/*https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5123366-utilisez-jpa-pour-communiquer-avec-une-base-de-donnees
 * 
 *   !!!!!!!! https://www.bezkoder.com/spring-boot-unit-test-jpa-repo-datajpatest/
 * 
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MedicalRecordsRepositoryTest {
	
	@Autowired
	private MedicalRecordsRepository mrr;
	
	@Autowired
	private TestEntityManager entityManager;

	
	Person pers;
	
	String allergies, medications;
			ArrayList<MedicalRecord> listmr = new ArrayList<>();
			//"all1","all2","all3"},  {"med1","med2"};
	MedicalRecord mr = new MedicalRecord("prennom", "nom", null, null, allergies, medications);
	
	@BeforeEach
	void initUseCase() {
		// pers = new Person("prenom", "nom", null, null, null, null, null);
		mr.setIdBd(mr.getFirstName()+"_"+mr.getLastName());
		mr.setPerson(pers);
		mrr.save(mr);
	}
	
	@AfterEach
	void destroyAll() {
		mrr.deleteAll();
	}
	
	@Test
	public void whenWeFindAll_thenAllTheMedicalRecordsInTheDatabaseAreGot() {
		assertThat(mrr.findAll()).hasSize(24).contains(mr);
	}
	
	@Test
	public void whenWeCreateANewMedicalRecord_thenHeIsSavedInTheDatabase() {
		MedicalRecord mr = mrr.save(new MedicalRecord(null, null, null, null, null, null));
		mr.setFirstName("Mams");
		mr.setAllergies(allergies);
		mr.setBirthdate(Date.valueOf(LocalDate.now().minusYears(19)));
		listmr.add(mr);
		assertThat(mrr.count()).isEqualTo(25); // 		assertThat(pr.findAll()).hasSize(2);
		assertThat(mrr.findByFirstName("Mams")).isEqualTo(listmr);
		assertThat(mr).hasFieldOrPropertyWithValue("birthdate", Date.valueOf(LocalDate.now().minusYears(19)));
	}
	
	@Test
	public void saveAll_success() {
		List<MedicalRecord> mrs = Arrays.asList(
                new MedicalRecord(null, null, null, null, null, null),
                new MedicalRecord(null, null, null, null, null, null),
                new MedicalRecord(null, null, null, null, null, null)
        );
        mrr.saveAll(mrs);
        assertThat(mrr.count()).isEqualTo(27);    
	}
	
	@Test
	public void givenAnIdBd_whenWeUseItToFindAPerson_thenThePersonWithThisIdDbIsGot() {
		assertThat((mrr.findByIdBd(mr.getIdBd()).get())).isEqualTo(mr);
	}
	
	@Test
	public void givenAnIdBd_whenWeUseItToDeleteAMedicalRecord_thenTheMedicalRecordWithThisIdDbIsDeleted(){
		mrr.deleteByIdBd(mr.getIdBd());
		assertThat(mrr.findAll()).hasSize(23);
	}
	
	@Test
	public void whenWeDeleteAllTheData_thenTheDatabaseIsEmpty(){
		mrr.deleteAll();
		assertThat(mrr.findAll()).isEmpty();
	}
	
	@Test
	public void givenAModification_whenWeApplyTheModificationAndWeSaveIt_thenTheMedicalRecordIsUpdatedWithTheModificationInTheDatabase() {
		mr.setFirstName("mlkj");
		mrr.save(mr);
		assertThat(mrr.count()).isEqualTo(24); // Verify if we haven't created a new person
		assertThat(mrr.findByIdBd(mr.getIdBd()).get().getFirstName()).isEqualTo(mr.getFirstName());
	}

}
