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
import com.openclassrooms.apisafetynet.model.MedicalRecord;
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

public class MedicalRecordControllerITOC {
	
	@Autowired
	private MockMvc mockMvc;
	
	private MedicalRecord mr = new MedicalRecord();
	
	@Autowired
	private TestEntityManager entityManager;
	
	String idDb = "testprenom"+"_"+"testnom";
	
	@BeforeEach
	 public void setUp() {
		mr.setIdBd(idDb);
		entityManager.persist(mr);
	}
	
	@AfterEach
	public void destroyAll() {
		entityManager.clear();
	}
	
	
	//---------- Méthodes de base --------
	@Test
	public void givenWeWantToDeleteAMedicalRecord_whenWeEnterTheDeleteEndpointWithTheIDBdOfTheMedicalRecord_thenAMessageForDeletingISDisplayed() throws Exception {
		
		
		MvcResult result = mockMvc.perform(delete("/medicalrecord/"+ idDb))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Medical Record deleted")))
		.andReturn();
		System.out.println(result.getResponse().getContentLength());
		assertThat(result.getResponse().getContentAsString()).contains("Medical Record deleted");
	}
	
	@Test
	public void givenWeWantToCheckAllTheMedicalRecordsInTheDatabase_whenWeEnterTheGetEndPoint_thenAllTheMedicalRecordsAreDisplayed() throws Exception {
		MvcResult result = mockMvc.perform(get("/medicalrecords"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].firstName", is("John")))
		.andReturn();(result.getResponse().getContentAsString())
				.contains("lastName\":\"Cooper");
	}

	@Test
	public void givenWeWantToSaveANewMedicalRecordInTheDatabase_whenWeSupplyMedicalRecordInformationAndWeEnterThePostEndPoint_thenAllTheMedicalRecordInformationAreReturned() throws Exception {
		MvcResult result = mockMvc.perform(post("/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Patric\", \"lastName\":\"lokj\", \"birthdate\":\"04/09/1847\", \"medications\":\"medoc1 : 500g,medoc2:.4mg\", \"allergies\":\"allergei1, allergei2\"}"))			
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("firstName", is("Patric")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("lastName\":\"lokj");
	}

	@Test
	public void givenWeWantToUpdateAMedicalRecordInTheDatabase_whenWeSupplyMedicalRecordInformationAndWeEnterThePutEndPoint_thenAllTheMedicalRecordInformationAreReturned() throws Exception {
		MvcResult result = mockMvc.perform(put("/medicalrecord/Foster_Shepard")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"04/09/1947\",  \"medications\":\"medoc1 : 10g,medoc2:.4mg\", \"allergies\":\"allergei1, allergei2\"}"))			
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("firstName", is("Foster")))
		.andReturn();
		
		assertThat(result.getResponse().getContentAsString())
				.contains("medoc1");
	}
	}
