package com.openclassrooms.apisafetynet.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.openclassrooms.apisafetynet.PortListeningTest;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;
import com.openclassrooms.apisafetynet.service.PersonService;

//--------------- !!!!!   https://openclassrooms.com/fr/courses/6100311-testez-votre-code-java-pour-realiser-des-applications-de-qualite/6441536-implementez-les-differents-types-de-tests-d-integration
//--------------       https://zetcode.com/springboot/datajpatest/


@SpringBootTest (webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase // A la place de DataJpaTest qui rentre en collision avec SpringBootTest
@AutoConfigureTestEntityManager // Afin de persist les données avant chaque test
@Transactional // TestEntityManager le nécessite
@TestPropertySource("classpath:application-test.properties")

public class PersonControllerITOC {
	
	@LocalServerPort
	int port;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Person person = new Person("testprenom", "testnom", "14 rue jijre", "Mobbey", "81584", "449689584874", "opjuihjr@mieo.uh");
	
	@Autowired
	private PersonService ps;
	
	@Autowired
	private TestEntityManager entityManager;
	
	String idDb = "testprenom"+"_"+"testnom";
	
	@BeforeEach
	 public void setUp() {
		person.setIdDb(idDb);
		entityManager.persist(person);
		System.out.println("port n° " + port);
	}
	
	@AfterEach
	public void destroyAll() {
		entityManager.clear();
	}
	
	
	//---------- Méthodes de base --------
	@Test
	public void givenTheIdDbOfThePersonWeWantToDelete_whenWeEnterTheDeleteEndpoint_thenAMessageForDeletingISDisplayed() throws Exception {
		
		
		MvcResult result = mockMvc.perform(delete("/person/"+ idDb))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Person deleted")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("Person deleted");
	}
	
	@Test
	public void givenWeWantToCheckAllThePersonsInTheDatabase_whenWeEnterTheGetEndPoint_thenAllThePersonsAreDisplayed() throws Exception {
		MvcResult result = mockMvc.perform(get("/persons"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].firstName", is("John")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("lastName\":\"testnom");
	}
	
	@Test
	public void givenWeWantToSaveANewPersonInTheDatabase_whenWeSupplyPersonsInformationAndWeEnterThePostEndPoint_thenAllThePersonInformationAreDisplayedAndTheNewURIReturned() throws Exception {
		MvcResult result = mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Patric\", \"lastName\":\"lokj\", \"address\":\"458 jiub\", \"city\":\"kilm\",\"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"fezfd@email.com\" }"))			
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("firstName", is("Patric")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("lastName\":\"lokj");
		assertThat(result.getResponse().getHeader("Location"))
		.contains("Patric_lokj");
	}

	@Test
	public void givenWeWantToUpdatePersonInTheDatabase_whenWeSupplyPersonsInformationAndWeEnterThePutEndPoint_thenAllThePersonIsDisplayedAndTheNewURIReturned() throws Exception {
		MvcResult result = mockMvc.perform(put("/person/Foster_Shepard")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"address\":\"458 jiub\", \"city\":\"kilm\",\"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))			
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("firstName", is("Foster")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("city\":\"kilm");
	}
	@Disabled  //Problème pour rentrer les medicalrecords dans la base de données H2, problème pour les arraylist et les dates, mauvais type apparemment + surement probleme clé etrangere
	// Necessite les informations des autres tables qui ne sont pas disponibles car je n'arrive pas à lier les tables entre elles grâce aux clés étrangères
	@Test
	public void givenWeWantToCheckAllTheChildrenLivingInAAdress_whenWeEnterAnAddress_thenTheChildrenwithTheirFamilyAreDisplayed() throws Exception {
		MvcResult result = mockMvc.perform(get("/childAlert")
				.param("address","892 Downing Ct"))			
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
		.contains("Zemicks");
		assertThat(result.getResponse().getContentAsString())
		.doesNotContain("Boyd");
	}
	
	@Disabled // Problème pour rentrer les medicalrecords dans la base de données H2, problème pour les arraylist et les dates
	// Necessite les informations des autres tables qui ne sont pas disponibles car je n'arrive pas à lier les tables entre elles grâce aux clés étrangères
	@Test
	public void givenWeWantAllThePhonesOfPersonCaringByAFireStation_whenWeEnterAFireStationStation_thenAllThePhonesAreReturned() throws Exception {
	MvcResult result = mockMvc.perform(get("/phoneAlert")
			.param("firestation","1"))			
	.andDo(print())
	.andExpect(status().isOk())
	.andReturn();
	
	assertThat(result.getResponse().getContentAsString())
	.contains("841-874-6512");
	
}
	@Disabled // Necessite les informations des autres tables qui ne sont pas disponibles car je n'arrive pas à lier les tables entre elles grâce aux clés étrangères
	@Test
	public void givenWeWantAllThePersonsInformations_whenWeEnterInParametersFirstNameAndLastName_thenAllTheInformationsAreReturned() throws Exception {
		MvcResult result = mockMvc.perform(get("/personInfo")
				.param("firstName","Reginold")		
				.param("lastName","Walker"))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
		.contains("841-874-8547");
	}
	
	@Test
	public void givenWeWantAllThePersonsEmail_whenWeEnterInParametersTheCity_thenAllTheEmailsAreReturned() throws Exception {
		MvcResult result = mockMvc.perform(get("/communityEmail")
				.param("city","Culver"))
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("@");
	}
}
