package com.openclassrooms.apisafetynet.e2e;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.RequestBodySpec;
import org.springframework.test.web.reactive.server.WebTestClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.server.WebHandler;
import reactor.core.publisher.Mono;


/*
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.openclassrooms.apisafetynet.controller.PersonController;
import com.openclassrooms.apisafetynet.service.PersonService;
*/
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)// (classes = PersonController.class PersonService.class)
//@AutoConfigureWebTestClient
@ActiveProfiles("integration-test")
public class TestsE2E {
	/*
	@Autowired
	RouterFunction<?> route;
	
	WebTestClient testClient = WebTestClient
			.bindToRouterFunction(route)
			.build();
	*/
	
	@Autowired
	WebTestClient testClient;
	
	@Autowired
	private Environment environment;

	@Autowired
	private ApplicationContext context;
	
	WebHandler handler = exchange -> Mono.empty();
	
	
	
	
	@BeforeEach
	void setUp() {  
		//testClient = WebTestClient.bindToApplicationContext(context).build();
		System.out.println(environment.getProperty("spring.application.name"));
		WebTestClient.bindToWebHandler(handler).build();

	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void whenWeEnterGetPersons_thenAllThePersonsAreReturned() {
	testClient.get().uri("/persons")
	.accept(MediaType.APPLICATION_JSON)
	.exchange()
	.expectStatus().
		isOk()
	.expectHeader()
		.contentType(MediaType.APPLICATION_JSON)
	.expectBody()
		.jsonPath("$[0].firstName").isEqualTo("John");
	}
	
	@Test
	public void givenWeWantPhoneNumbers_whenWeEnterASationAndWeEnterGetPhoneAlert_thenAllThePhoneNumbersAreReturned() {
	
		String s=new String(testClient.get()
				.uri(uriBuilder -> uriBuilder.path("/phoneAlert").queryParam("firestation", 3).build())
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
	            .expectBody().returnResult().getResponseBody());

	System.out.println(s);
	testClient.get()
	.uri(uriBuilder -> uriBuilder.path("/phoneAlert").queryParam("firestation", 3).build())
	.accept(MediaType.APPLICATION_JSON)
	.exchange()
	.expectStatus().
		isOk()
	.expectHeader()
		.contentType(MediaType.APPLICATION_JSON)
	.expectBody()
		.jsonPath("$[0].phone").isEqualTo("841-874-6512")
		.jsonPath("$[11].phone").doesNotExist()
		.jsonPath("$[0].firstName").doesNotExist();	
	}

	@Test
	public void givenWeWantChildAlert_whenWeEnterAnAddressAndWeEnterGetChildAlert_thenAllTheChildAlertssAreReturned() {
		String s=new String(testClient.get()
				.uri(uriBuilder -> uriBuilder.path("/childAlert").queryParam("address", "947 E. Rose Dr").build())
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
	            .expectBody().returnResult().getResponseBody());

	System.out.println(s);
	ArrayList<String> members = new ArrayList<String>();
	members.add("Stelzer Brian");
	members.add("Stelzer Shawna");
	testClient.get()
	.uri(uriBuilder -> uriBuilder.path("/childAlert").queryParam("address", "947 E. Rose Dr").build())
	.accept(MediaType.APPLICATION_JSON)
	.exchange()
	.expectStatus().
		isOk()
	.expectHeader()
		.contentType(MediaType.APPLICATION_JSON)
	.expectBody()
		.jsonPath("$[0]childAlert.lastName").isEqualTo("Stelzer")
		.jsonPath("$[0]childAlert.medicalRecord_Birthdate").isEqualTo("03/05/2014")
		.jsonPath("$[0].familyMembers").isArray()
		.jsonPath("$[0].familyMembers").isEqualTo(members)//"Stelzer Brian","Stelzer Shawna"
		.jsonPath("$[0].phone").doesNotExist()
		.jsonPath("$[1].firstName").doesNotExist();	
	}
	
	@Test
	public void givenWeWantPersonInfos_whenWeEnterAFirstNameAndWeEnterALastNameAndAndWeEnterGetPersonInfoAllThePersonInfosAreReturned() {
		String s=new String(testClient.get()
				.uri(uriBuilder -> uriBuilder.path("/personInfo").queryParam("firstName", "Sophia").queryParam("lastName", "Zemicks").build())
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
	            .expectBody().returnResult().getResponseBody());

	System.out.println(s);
	
	testClient.get()
	.uri(uriBuilder -> uriBuilder.path("/personInfo").queryParam("firstName", "Sophia").queryParam("lastName", "Zemicks").build())
	.accept(MediaType.APPLICATION_JSON)
	.exchange()
	.expectStatus().
		isOk()
	.expectHeader()
		.contentType(MediaType.APPLICATION_JSON)
	.expectBody()
		.jsonPath("$[0].email").isEqualTo("soph@email.com")
		.jsonPath("$[0].medicalRecord_Birthdate").isEqualTo("03/05/1988")
		.jsonPath("$[0].medicalRecord_Allergies").isEqualTo("peanut, shellfish, aznol")
		.jsonPath("$[0].medicalRecord_Medications").isEqualTo("aznol:60mg, hydrapermazol:900mg, pharmacol:5000mg, terazine:500mg")
		.jsonPath("$[6].phone").doesNotExist()
		.jsonPath("$[2].firstName").doesNotExist();	
	}
}
