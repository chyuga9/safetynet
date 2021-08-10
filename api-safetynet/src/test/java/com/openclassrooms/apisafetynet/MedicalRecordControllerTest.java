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
import com.openclassrooms.apisafetynet.controller.MedicalRecordController;
import com.openclassrooms.apisafetynet.service.FireStationService;
import com.openclassrooms.apisafetynet.service.MedicalRecordService;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MedicalRecordService mS;
	
	static int x;
	
	String random = "random";
	
	//JSONObject json = Mockito.mock(JSONObject.class);
	
	//public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.JSON_UTF_8.type(), MediaType.JSON_UTF_8.subtype(), MediaType.JSON_UTF_8.parameters()); //
	//public static final MediaType APPLICATION_JSON_UTF82 = new MediaType(null, null, null);
	
	@BeforeAll
	static public void setup() {
		x = (int) (Math.random()*((10-1)+1))+1; // nombre entier au hasard - double x = (int)(Math.random()*((max-min)+1))+min;
	}
	
	//---------- Méthodes de base --------

	
	@Test
	public void testDeleteMedicalRecord() throws Exception {
		mockMvc.perform(
				delete("/medicalrecord/"+ Mockito.anyInt())).andExpect(status().isOk());	}
	
	@Test
	public void testGetMedicalRecords() throws Exception{
		mockMvc.perform(get("/medicalrecord")).andExpect(status().isOk());
	}
	// Problème avec ce test, retourne un code 204 pcq il considère que p (de la méthode original est null)...
	// Mais je me demande pourquoi pour le test d'une personne il fait ça mais pas quand il y a plusieurs personnnes...
	
	 @Test
	 
	public void testPostMedicalRecord() throws Exception{
		mockMvc.perform(
				post("/medicalrecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"adress\":\"sqqsdf\"}"))
				//.content("{\""+Mockito.anyString()+"\"}"))
		.andExpect(status().
				isCreated());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   
	}

	@Test
	public void testPostMedicalRecords() throws Exception{
		mockMvc.perform(
				post("/medicalrecords")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"address\":\"sqqsdf\", \"station\":"+5+"},\n"
						+ "{\"address\":\"rqzed\", \"station\":"+5+" }]"))
				//.content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}]"))
		.andExpect(status().
				isCreated());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   
	}
	
	@Test
	public void testPutMedicalRecord() throws Exception{
		mockMvc.perform(
				put("/medicalrecord/"+x)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\""+Mockito.anyString()+"\":\""+Mockito.anyString()+"\"}"))
		.andExpect(status().isOk());
				//put("/person/"+x).contentType(MediaType.JSON_UTF_8.subtype()).content(("{\"mykey\":\"myvalue\"}"))).andExpect(status().isOk());
		// perform(post("/myapi").contentType(MediaType.APPLICATION_JSON).content("{\"mykey\":\"myvalue\"}"))   

	}
	
}
