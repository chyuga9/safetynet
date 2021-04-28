package com.openclassrooms.apisafetynet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiSafetynetApplication {
	
	private static Logger logger = LogManager.getLogger("ApiSafetynetApplication");
	
	public static void main(String[] args) {
		SpringApplication.run(ApiSafetynetApplication.class, args);
		logger.info("Lancement de l'application");
	}

}
