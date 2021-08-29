package com.openclassrooms.apisafetynet.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.openclassrooms.apisafetynet.PersonRepositoryTest;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;
import com.openclassrooms.apisafetynet.service.PersonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static Person person = new Person("testprenom", "testnom", "14 rue jijre", "Mobbey", "81584", "449689584874", "opjuihjr@mieo.uh");
	
	@Autowired
	private PersonService ps;
	
	@Autowired
	private PersonsRepository pr;
	
	//@Autowired
	//private TestEntityManager entityManager;
	/*
	@BeforeAll
	 static public void setUp() {
		//entityManager.persist(person);
	}
	*/
	
	//---------- Méthodes de base --------
/*
	@Test
	public void testDeletePerson() throws Exception {
		
		
		mockMvc.perform(delete("/person/"+person.getIdDb()))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Person deleted")));
	}
	
	@Test
	public void testGetPersons() throws Exception {
		mockMvc.perform(get("/persons"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].firstName", is("John")));
	}

*/
	//---------- Méthodes personalisées --------

	// ---------- Tests avec l'exemple de baeldung ---> https://www.baeldung.com/integration-testing-in-spring
		
	/* Je ne fais pas ce test car il fait appelle à d'autres classes qui ne sont plus nécessaires grâce à @SpringBootTest
	 * 
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
	    ServletContext servletContext = webApplicationContext.getServletContext(); 
	    assertNotNull(servletContext);
	    assertTrue(servletContext instanceof MockServletContext);
	    assertNotNull(webApplicationContext.getBean("greetController"));
	}
	
	* Dans le cas où on avait des pages HTML
	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() {
    this.mockMvc.perform(get("/homePage")).andDo(print())
      .andExpect(view().name("index"));
}

	
	
	@Test
	public void givenGetEndpoint_whenMockMVC_thenWeGetAllThePersons() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/persons"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("testprenom"))
				.andReturn();
		assertThat(mvcResult.getResponse().getContentType()).isEqualTo("application/json;charset=UTF-8");
	}
	*/
}
