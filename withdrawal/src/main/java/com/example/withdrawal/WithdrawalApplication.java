package com.example.withdrawal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WithdrawalApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithdrawalApplication.class, args);
	}

}

