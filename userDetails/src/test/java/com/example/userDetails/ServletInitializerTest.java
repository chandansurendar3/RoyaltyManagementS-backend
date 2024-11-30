package com.example.userDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ExtendWith(MockitoExtension.class)
public class ServletInitializerTest {

    @Test
    public void testConfigure() {
        // Given
        SpringApplicationBuilder applicationBuilder = Mockito.mock(SpringApplicationBuilder.class);
        ServletInitializer servletInitializer = new ServletInitializer();

        // When
        SpringApplicationBuilder result = servletInitializer.configure(applicationBuilder);

        // Then
        assertEquals(null, result);
        verify(applicationBuilder).sources(UserDetailsApplication.class);
    }

   

    @Test
    public void testServletInitializer() {
        // Given
        ServletInitializer servletInitializer = new ServletInitializer();

        // When
        servletInitializer.configure(Mockito.mock(SpringApplicationBuilder.class));

        // Then
        // No exception thrown
    }
}

