package com.openclassrooms.apisafetynet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.MediaType;

import com.jayway.jsonpath.JsonPath;
import com.openclassrooms.apisafetynet.controller.PersonController;
import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.repository.PersonsRepository;
import com.openclassrooms.apisafetynet.service.PersonService;

/*
 * https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
 * https://spring.io/guides/gs/testing-web/
 * Donne des exemples pour faire des testes de controller
 */
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService ps;
	
	 static int x;
	
	//JSONObject json = Mockito.mock(JSONObject.class);
	
	//public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.JSON_UTF_8.type(), MediaType.JSON_UTF_8.subtype(), MediaType.JSON_UTF_8.parameters()); //
	//public static final MediaType APPLICATION_JSON_UTF82 = new MediaType(null, null, null);
	
	@BeforeAll
	 static public void setup() {
		x = (int) (Math.random()*((10-1)+1))+1; // nombre entier au hasard - double x = (int)(Math.random()*((max-min)+1))+min;
	}
		
	//---------- Méthodes de base --------

	//Tester si la personne créée en Set up a bien été supprimée
	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person/"+ x))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Person deleted")));
	}
	
	// Tester si la personne créée est bien celle obtenue dans le json -  Pour l'instant je n'arrive à rien afficher dans le body du test
	@Test
	public void testGetPersons() throws Exception{

		//when(ps.savePerson(person)).thenReturn(person);
		mockMvc.perform(get("/persons"))
		.andDo(print())
		.andExpect(status().isOk());
		
		
		//.andExpect(jsonPath("$.persons").exists());
		//System.out.println(JsonPath.read(uri));
		//.andExpect(jsonPath("$",containsString("5")));
	}
	// Problème avec ce test, retourne un code 204 pcq il considère que p (de la méthode original est null)...
	// Mais je me demande pourquoi pour le test d'une personne il fait ça mais pas quand il y a plusieurs personnnes...
	
	 @Test
	 
	public void testPostPerson() throws Exception{
		mockMvc.perform(
				post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"sqqsdf\", \"lastName\":\"Bogqfgqqyd\", \"address\":\"1509 Culver St\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
				//.content("{\""+Mockito.anyString()+"\"}"))
		.andExpect(status().
				isCreated());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   
	}

	@Test
	public void testPostPersons() throws Exception{
		mockMvc.perform(
				post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"firstName\":\"sqqsdf\", \"lastName\":\"Bogqfgqqyd\",\"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" },\n"
						+ "{\"firstName\":\"sqqsdf\", \"lastName\":\"Bogqfgqqyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }]"))
				//.content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}]"))
		.andExpect(status().
				isCreated());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   
	}
	
	@Test
	public void testPutPersons() throws Exception{
		mockMvc.perform(
				put("/person/"+x)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}"))
		.andExpect(status().isOk());
				//put("/person/"+x).contentType(MediaType.JSON_UTF_8.subtype()).content(("{\"mykey\":\"myvalue\"}"))).andExpect(status().isOk());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   

	}
	
	//---------- Méthodes personalisées --------

	@Test
	public void testGetChildAlert() throws Exception{
		mockMvc.perform(get("/childAlert")
				.param("address",Mockito.anyString()))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testGetPhones() throws Exception{
		mockMvc.perform(get("/phoneAlert")
				.param("firestation",String.valueOf(x)))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testGetPersonInfo() throws Exception{
		/*
		 * LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		 * requestParams.add("firstName", "sfdsqg");
		requestParams.add("lastName", "gfequi");
		mockMvc.perform(get("/personInfo").params(requestParams)).andExpect(status().isOk());
		 */
		mockMvc.perform(get("/personInfo")
				.param("firstName",Mockito.anyString())
				.param("lastName", Mockito.anyString()))
		.andExpect(status().isOk());
	}

	@Test
	public void testGetEmail() throws Exception{
		mockMvc.perform(get("/communityEmail")
				.param("city", Mockito.anyString()))
		.andExpect(status().isOk());
	}
	
	// ---- Autre manière de tester 
	//https://www.oodlestechnologies.com/blogs/how-to-unit-test-a-post-rest-service-using-mockito-with-junit/
	/*
	 * @Test
public void createStudentCourse() throws Exception {
    Course mockCourse = new Course("1", "Smallest Number", "1",Arrays.asList("1", "2", "3", "4"));

    // studentService.addCourse to respond back with mockCourse
    Mockito.when(studentService.addCourse(Mockito.anyString(),Mockito.any(Course.class))).thenReturn(mockCourse);

    // Send course as body to /students/Student1/courses
    RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/students/Student1/courses")
            .accept(MediaType.APPLICATION_JSON)
            .content(exampleCourseJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    MockHttpServletResponse response = result.getResponse();

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    assertEquals("http://localhost/students/Student1/courses/1",response.getHeader(HttpHeaders.LOCATION));
}*/
	
	 
}
