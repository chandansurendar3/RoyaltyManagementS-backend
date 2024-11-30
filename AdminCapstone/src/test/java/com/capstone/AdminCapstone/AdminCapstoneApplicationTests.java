package com.capstone.AdminCapstone;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
 
import org.springframework.boot.SpringApplication;
 
class AdminCapstoneApplicationTest {
 
    @Test
    void main_InvokesSpringApplicationRun() {
        // Arrange
        String[] args = {}; // Assuming no command line arguments are passed
        SpringApplication springApplicationMock = mock(SpringApplication.class);
 
        // Act
        AdminCapstoneApplication.main(args);
 
        verify(springApplicationMock);
    }
}
