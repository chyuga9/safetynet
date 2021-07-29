package com.openclassrooms.apisafetynet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
import com.openclassrooms.apisafetynet.controller.PersonController;
import com.openclassrooms.apisafetynet.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService ps;
	
	static int x;
	
	
	
	//@MockBean
	private JSONObject json = new JSONObject();
	
	//JSONObject json = Mockito.mock(JSONObject.class);
	
	//public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.JSON_UTF_8.type(), MediaType.JSON_UTF_8.subtype(), MediaType.JSON_UTF_8.parameters()); //
	//public static final MediaType APPLICATION_JSON_UTF82 = new MediaType(null, null, null);
	
	@BeforeAll
	static public void setup() {
		x = (int) (Math.random()*((10-1)+1))+1; // nombre entier au hasard - double x = (int)(Math.random()*((max-min)+1))+min;
	}
	
	//---------- Méthodes de base --------

	
	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person/"+x)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetPersons() throws Exception{
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}
	
	@Test
	public void testPostPerson() throws Exception{
		mockMvc.perform(
				post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				//.content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}"))
				.content("{\"firstName\":\"sqqsdf\", \"lastName\":\"Bogqfgqqyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }"))
		.andExpect(status()
				.isCreated());
	}
	
	@Test
	public void testPostPersons() throws Exception{
		mockMvc.perform(
				post("/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"firstName\":\"sqqsdf\", \"lastName\":\"Bogqfgqqyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" },\n"
						+ "{\"firstName\":\"sqqsdf\", \"lastName\":\"Bogqfgqqyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }]"))
				//.content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}]"))
		.andExpect(status().
				isCreated());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   
	}
	
	@Test
	public void testPutPersons() throws Exception{
		mockMvc.perform(
				put("/person/"+x).contentType(MediaType.APPLICATION_JSON).content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}")).andExpect(status().isOk());
				//put("/person/"+x).contentType(MediaType.JSON_UTF_8.subtype()).content(("{\"mykey\":\"myvalue\"}"))).andExpect(status().isOk());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   

	}
	
	//---------- Méthodes personalisées --------

	@Test
	public void testGetChildAlert() throws Exception{
		mockMvc.perform(get("/childAlert").param("address",Mockito.anyString())).andExpect(status().isOk());
	}
	
	@Test
	public void testGetPhones() throws Exception{
		mockMvc.perform(get("/phoneAlert").param("firestation",String.valueOf(x))).andExpect(status().isOk());
	}
	
	@Test
	public void testGetPersonInfo() throws Exception{
		/*
		 * LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		 * requestParams.add("firstName", "sfdsqg");
		requestParams.add("lastName", "gfequi");
		mockMvc.perform(get("/personInfo").params(requestParams)).andExpect(status().isOk());
		 */
		mockMvc.perform(get("/personInfo").param("firstName",Mockito.anyString()).param("lastName", Mockito.anyString())).andExpect(status().isOk());
	}

	@Test
	public void testGetEmail() throws Exception{
		mockMvc.perform(get("/communityEmail").param("city", Mockito.anyString())).andExpect(status().isOk());
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
