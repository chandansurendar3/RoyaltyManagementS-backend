package com.example.rms_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RmsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmsMicroserviceApplication.class, args);
	}

}
