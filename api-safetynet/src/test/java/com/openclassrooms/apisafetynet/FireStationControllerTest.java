package com.openclassrooms.apisafetynet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.http.MediaType;

import com.openclassrooms.apisafetynet.controller.FireStationController;
import com.openclassrooms.apisafetynet.service.FireStationService;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FireStationService fS;
	
	static int x;
	
	//JSONObject json = Mockito.mock(JSONObject.class);
	
	//public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.JSON_UTF_8.type(), MediaType.JSON_UTF_8.subtype(), MediaType.JSON_UTF_8.parameters()); //
	//public static final MediaType APPLICATION_JSON_UTF82 = new MediaType(null, null, null);
	
	@BeforeAll
	static public void setup() {
		x = (int) (Math.random()*((10-1)+1))+1; // nombre entier au hasard - double x = (int)(Math.random()*((max-min)+1))+min;
	}
	
	//---------- Méthodes de base --------

	
	@Test
	public void testDeleteFireStation() throws Exception {
		mockMvc.perform(
				delete("/firestation/"+ Mockito.anyInt())).andExpect(status().isOk());	}
	
	@Test
	public void testGetFireStations() throws Exception{
		mockMvc.perform(get("/firestation")).andExpect(status().isOk());
	}
	// Problème avec ce test, retourne un code 204 pcq il considère que p (de la méthode original est null)...
	// Mais je me demande pourquoi pour le test d'une personne il fait ça mais pas quand il y a plusieurs personnnes...
	
	 @Test
	 
	public void testPostFireStation() throws Exception{
		mockMvc.perform(
				post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"adress\":\"sqqsdf\"}"))
				//.content("{\""+Mockito.anyString()+"\"}"))
		.andExpect(status().
				isCreated());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   
	}

	@Test
	public void testPostFireStations() throws Exception{
		mockMvc.perform(
				post("/firestations")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"address\":\"sqqsdf\", \"station\":"+5+"},\n"
						+ "{\"address\":\"rqzed\", \"station\":"+5+" }]"))
				//.content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}]"))
		.andExpect(status().
				isCreated());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   
	}
	
	@Test
	public void testPutFireStation() throws Exception{
		mockMvc.perform(
				put("/firestation").contentType(MediaType.APPLICATION_JSON)
				.content("{\"adress\":\"sqqsdf\"}"))
		.andExpect(status().isOk());
				//put("/person/"+x).contentType(MediaType.JSON_UTF_8.subtype()).content(("{\"mykey\":\"myvalue\"}"))).andExpect(status().isOk());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   

	}
	
	//---------- Méthodes personalisées --------

	@Test
	public void testGetPeople() throws Exception{
		/*
		 * LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		 * requestParams.add("firstName", "sfdsqg");
		requestParams.add("lastName", "gfequi");
		mockMvc.perform(get("/personInfo").params(requestParams)).andExpect(status().isOk());
		 */
		mockMvc.perform(get("/firestation").param("stationNumber",String.valueOf(x))).andExpect(status().isOk());
	}
	
	@Test
	public void testGetFireAlert() throws Exception{
		mockMvc.perform(get("/fire").param("address",Mockito.anyString())).andExpect(status().isOk());
	}
	
	@Test
	public void testGetFloodAlert() throws Exception{
		/*
		 * LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		 * requestParams.add("firstName", "sfdsqg");
		requestParams.add("lastName", "gfequi");
		mockMvc.perform(get("/personInfo").params(requestParams)).andExpect(status().isOk());
		 */
		mockMvc.perform(get("/flood/stations").param("stations",Mockito.anyString())).andExpect(status().isOk());
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
