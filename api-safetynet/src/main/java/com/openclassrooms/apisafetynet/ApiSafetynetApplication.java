package com.openclassrooms.apisafetynet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApiSafetynetApplication {
	
	private static final Logger logger = LogManager.getLogger(ApiSafetynetApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApiSafetynetApplication.class, args);
		logger.info("Lancement de l'application");
	}

}
