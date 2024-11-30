package com.RoyalityManagement.ArtistRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
public class ServletInitializerTest {

    @Test
    public void testConfigure() {
        ServletInitializer servletInitializer = new ServletInitializer();
        SpringApplicationBuilder builder = new SpringApplicationBuilder();

        SpringApplicationBuilder configuredBuilder = servletInitializer.configure(builder);
        
        // Check if the application class is correctly set
        assertNotNull(configuredBuilder);
        assertTrue(configuredBuilder.application().getAllSources().contains(ArtistRequestApplication.class));
    }
}
