package com.openclassrooms.apisafetynet;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

//---- ------ !!!!!  Peut thrown une IllegalStateException : failed context loaded parce que wampserver n'a pas été démarré
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PortListeningTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		System.out.println(port);
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("");
	}
}

