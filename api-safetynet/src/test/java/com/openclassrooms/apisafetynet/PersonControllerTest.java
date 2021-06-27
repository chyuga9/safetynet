package com.openclassrooms.apisafetynet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.apisafetynet.controller.PersonController;
import com.openclassrooms.apisafetynet.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService ps;
	
	//---------- Méthodes de base --------

	
	@Test
	public void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person/{id_bd}")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetPersons() throws Exception{
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}
	
	@Test
	public void testPostPerson() throws Exception{
		mockMvc.perform(post("/person")).andExpect(status().isOk());
	}
	
	@Test
	public void testPostPersons() throws Exception{
		mockMvc.perform(post("/persons")).andExpect(status().isOk());
	}
	
	@Test
	public void testPutPersons() throws Exception{
		mockMvc.perform(put("/persons")).andExpect(status().isOk());
	}
	
	//---------- Méthodes personalisées --------

	@Test
	public void testGetChildAlert() throws Exception{
		mockMvc.perform(get("/childAlert")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetPhones() throws Exception{
		mockMvc.perform(get("/phoneAlert")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetPersonInfo() throws Exception{
		mockMvc.perform(get("/personInfo")).andExpect(status().isOk());
	}

	@Test
	public void testGetEmail() throws Exception{
		mockMvc.perform(get("/CommunityEmal")).andExpect(status().isOk());
	}
}
