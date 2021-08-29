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
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;
import com.openclassrooms.apisafetynet.service.PersonService;

//--------------- !!!!!   https://openclassrooms.com/fr/courses/6100311-testez-votre-code-java-pour-realiser-des-applications-de-qualite/6441536-implementez-les-differents-types-de-tests-d-integration
//--------------       https://zetcode.com/springboot/datajpatest/


@SpringBootTest //(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase // A la place de DataJpaTest qui rentre en collision avec SpringBootTest
@AutoConfigureTestEntityManager // Afin de persist les données avant chaque test
@Transactional // TestEntityManager le nécessite
@TestPropertySource("classpath:application-test.properties")

public class FireStationsControllerITOC {
	
	@Autowired
	private MockMvc mockMvc;
	
	private FireStation fs = new FireStation();

	@Autowired
	private TestEntityManager entityManager;
	
	int x;
	
	@BeforeEach
	 public void setUp() {
		entityManager.persist(fs);
		x = (int) (Math.random()*((13-1)+1))+1; // nombre entier au hasard - double x = (int)(Math.random()*((max-min)+1))+min;
	}
	
	@AfterEach
	public void destroyAll() {
		entityManager.clear();
	}
	
	
	//---------- Méthodes de base --------
	@Test
	public void givenTheIdOfTheFireStationWeWantToDelete_whenWeEnterTheDeleteEndpoint_thenAMessageForDeletingISDisplayed() throws Exception {
		
		
		MvcResult result = mockMvc.perform(delete("/firestation/"+ x))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Firestation deleted")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString()).contains("Firestation deleted");
	}
	
	@Test
	public void givenWeWantToCheckAllTheFireStationsInTheDatabase_whenWeEnterTheGetEndPoint_thenAllTheFireStationsAreDisplayed() throws Exception {
		MvcResult result = mockMvc.perform(get("/firestation"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].address", is("1509 Culver St")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("address\":\"112 Steppes Pl");
	}
	
	@Test
	public void givenWeWantToSaveANewFireStationInTheDatabase_whenWeSupplyFireStationsInformationAndWeEnterThePostEndPoint_thenAllTheFireStationInformationAreDisplayed() throws Exception {
		MvcResult result = mockMvc.perform(post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"address\":\"une adresse\", \"station\":\"8\"}"))			
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("address", is("une adresse")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("address\":\"une adresse");
	}

	@Test
	public void givenWeWantToUpdateAFireStationInTheDatabase_whenWeSupplyFireStationsInformationAndWeEnterThePutEndPoint_thenAllTheFireStationIsDisplayed() throws Exception {
		MvcResult result = mockMvc.perform(put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"8\", \"address\":\"748 Townings Dr\", \"station\":\"854\"}"))			
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("station", is(854)))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("station\":"+854);
	}
	@Disabled // Necessite les informations des autres tables qui ne sont pas disponibles car je n'arrive pas à lier les tables entre elles grâce aux clés étrangères
	@Test
	public void givenWeWantToCheckAllThePersonsCaringByAFireStation_whenWeEnterAStationNumber_thenAllThePeopleOfTheZoneAreReturned() throws Exception {
		MvcResult result = mockMvc.perform(get("/firestation")
				.param("stationNumber","3"))			
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
		.contains("Carman");
		assertThat(result.getResponse().getContentAsString())
		.doesNotContain("Boyd");
	}
	
	@Disabled // Necessite les informations des autres tables qui ne sont pas disponibles car je n'arrive pas à lier les tables entre elles grâce aux clés étrangères
	@Test
	public void givenWeWantAllTheImportantInformationssOfPersonLivingInAnAddress_whenWeEnterAnAddress_thenAllTheInformationsAreReturned() throws Exception {
	MvcResult result = mockMvc.perform(get("/fire")
			.param("address","947 E. Rose Dr"))			
	.andDo(print())
	.andExpect(status().isOk())
	.andReturn();
	
	assertThat(result.getResponse().getContentAsString())
	.contains("Stelzer");
	assertThat(result.getResponse().getContentAsString())
	.doesNotContain("Boyd");
}
	@Disabled // Necessite les informations des autres tables qui ne sont pas disponibles car je n'arrive pas à lier les tables entre elles grâce aux clés étrangères
	@Test
	public void givenWeWantAllThePersonsInformations_whenWeEnterInParametersTheStations_thenAllTheInformationsAreReturned() throws Exception {
		MvcResult result = mockMvc.perform(get("/flood/stations")
				.param("stations","2"))		
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
		.contains("Cardigan");
		assertThat(result.getResponse().getContentAsString())
		.doesNotContain("Boyd");	}
	
}
