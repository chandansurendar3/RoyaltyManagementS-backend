package com.capstone.AlertCapstone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlertCapstoneApplicationTests {

	@Test
	public void applicationContextLoaded() {
	}

	@Test
	public void applicationContextTest() {
	    AlertCapstoneApplication.main(new String[] {});
	}
}
