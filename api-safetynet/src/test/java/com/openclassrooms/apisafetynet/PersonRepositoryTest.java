package com.openclassrooms.apisafetynet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
public class PersonRepositoryTest {

	
	@Autowired
	private PersonsRepository pr;
	/*
	@Autowired
	private TestEntityManager entityManager;
*/
	
	Person pers;
	

	
	@BeforeEach
	void initUseCase() {
		 pers = new Person("prenom", "nom", null, null, null, null, null);
		List<Person> persons = Arrays.asList(pers);
		pers.setIdDb(pers.getFirstName()+"_"+pers.getLastName());
		pr.saveAll(persons); // Je pourrais également utiliser TestEntityManager.persist(persons);
	}
	
	
	@Test
	public void whenWeFindAll_thenAllThePersonsInTheDatabaseAreGot() {
		assertThat(pr.findAll()).hasSize(24).contains(pers);
	}
	
	@Test
	public void whenWeCreateANewPerson_thenHeIsSavedIntheDatabase() {
		Person person = pr.save(new Person("sajedul", "karim", "01737186095", null, null, null, null));
		assertThat(pr.count()).isEqualTo(25); // 		assertThat(pr.findAll()).hasSize(2);
		assertThat(pr.findByLastName("karim")).isNotEmpty();
		assertThat(person).hasFieldOrPropertyWithValue("lastName", "karim");
	}
	
	@Test
	public void saveAll_success() {
		List<Person> persons = Arrays.asList(
                new Person("sajedul", "karim", "01737186095", null, null, null, null),
                new Person("nafis", "khan", "01737186096", null, null, null, null),
                new Person("aayan", "karim", "01737186097", null, null, null, null)
        );
        pr.saveAll(persons);
        assertThat(pr.count()).isEqualTo(27);    
	}
	
	@Test
	public void givenAnIdDb_whenWeUseItToFindAPerson_thenThePersonWithThisIdDbIsGot() {
		assertThat((pr.findByIdDb(pers.getIdDb()).get())).isEqualTo(pers);
	}
	
	@Test
	public void givenAnIdDb_whenWeUseItToDeleteAPerson_thenThePersonWithThisIdDbIsDeleted(){
		pr.deleteByIdDb(pers.getIdDb());
		assertThat(pr.count()).isEqualTo(23);
	}
	
	@Test
	public void whenWeUseItToDeleteAllTheData_thenTheDatabaseIsEmpty(){
		pr.deleteAll();
		assertThat(pr.findAll()).isEmpty();
	}
	
	@Test
	public void givenAModification_whenWeApplyTheModificationAndWeSaveIt_thenThePersonIsUpdatedWithTheModificationInTheDatabase() {
		pers.setAddress("mlkj");
		pr.save(pers);
		assertThat(pr.count()).isEqualTo(24); // Verify if we haven't created a new person
		assertThat(pr.findByIdDb(pers.getIdDb()).get().getAddress()).isEqualTo(pers.getAddress());
	}

}
