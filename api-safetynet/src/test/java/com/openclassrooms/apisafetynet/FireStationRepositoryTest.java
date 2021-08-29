package com.openclassrooms.apisafetynet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.FireStationsRepository;
import com.openclassrooms.apisafetynet.repository.MedicalRecordsRepository;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;

/*https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5123366-utilisez-jpa-pour-communiquer-avec-une-base-de-donnees
 * 
 *   !!!!!!!! https://www.bezkoder.com/spring-boot-unit-test-jpa-repo-datajpatest/
 * 
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FireStationRepositoryTest {
	

	@Autowired
	private FireStationsRepository fsr;
	
	@Autowired
	private TestEntityManager entityManager;

	
	FireStation fs;
	
	List<FireStation> fireStations;
	
	// ---- !! ?e pas supprimer, sinon déclenche un problème de cchargement de contexte lors du lancement des tests
	@MockBean
	MedicalRecordsRepository mrr;
	
	@BeforeEach
	void initUseCase() {
		 fs = new FireStation();
		fireStations = Arrays.asList(fs);
		fsr.save(fs);
	}
	
	@AfterEach
	void destroyAll() {
		fsr.deleteAll();
	}
	
	@Test
	public void whenWeFindAll_thenAllTheFireStationsInTheDatabaseAreGot() {
		assertThat(fsr.findAll()).hasSize(14).contains(fs);
	}
	
	@Test
	public void whenWeCreateANewFireStation_thenItIsSavedIntheDatabase() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("poinpoin");
			fsr.save(fireStation);
		assertThat(fsr.count()).isEqualTo(15); // 		assertThat(pr.findAll()).hasSize(2);
		assertThat(fsr.findByAddress("poinpoin")).isNotEmpty();
		assertThat(fsr.findByAddress("poinpoin").get()).hasFieldOrPropertyWithValue("address", "poinpoin");
	}
	
	@Test
	public void saveAll_success() {
		List<FireStation> fireStations = Arrays.asList(
                new FireStation(),
                new FireStation(),
                new FireStation()
        );
        fsr.saveAll(fireStations);
        assertThat(fsr.count()).isEqualTo(17);    
	}
	
	@Test
	public void givenAnAddress_whenWeUseItToFindAFireStation_thenTheFireStationWithThisIdDbIsGot() {
		assertThat((fsr.findByAddress(fs.getAddress()).get())).isEqualTo(fs);
	}
	
	@Test
	public void givenAnId_whenWeUseItToDeleteAFireStation_thenTheFireStationWithThisIdDbIsDeleted(){
		fsr.deleteById(fs.getId());
		assertThat(fsr.count()).isEqualTo(13);
	}
	
	@Test
	public void whenWeUseItToDeleteAllTheData_thenTheDatabaseIsEmpty(){
		fsr.deleteAll();
		assertThat(fsr.findAll()).isEmpty();
	}
	
	@Test
	public void givenAModification_whenWeApplyTheModificationAndWeSaveIt_thenTheFireStationIsUpdatedWithTheModificationInTheDatabase() {
		fs.setAddress("mlkj");
		fsr.save(fs);
		assertThat(fsr.count()).isEqualTo(14); // Verify if we haven't created a new person
		assertThat(fsr.findById(fs.getId()).get().getAddress()).isEqualTo(fs.getAddress());
	}

}
