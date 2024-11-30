package com.capstone.AdminCapstone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ServletInitializerTest {

    private ServletInitializer servletInitializer;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        // Any class-level setup if needed
    }

    @AfterEach
    void tearDown() throws Exception {
        // Any cleanup after each test
    }

    @Test
    void testConfigure() {
        // Given
        servletInitializer = new ServletInitializer();
        SpringApplicationBuilder builder = new SpringApplicationBuilder();

        // When
        SpringApplicationBuilder result = servletInitializer.configure(builder);

        // Then
        assertNotNull(result, "SpringApplicationBuilder should not be null");

        // Ensure AdminCapstoneApplication.class is part of the sources
        boolean isSourcePresent = false;
        try {
            // Access the internal sources field directly via reflection
            java.lang.reflect.Field sourcesField = SpringApplicationBuilder.class.getDeclaredField("sources");
            sourcesField.setAccessible(true);
            Object sources = sourcesField.get(result);
            if (sources instanceof java.util.Set) {
                @SuppressWarnings("unchecked")
                java.util.Set<Class<?>> sourcesSet = (java.util.Set<Class<?>>) sources;
                isSourcePresent = sourcesSet.contains(AdminCapstoneApplication.class);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to access sources field in SpringApplicationBuilder");
        }

        assertTrue(isSourcePresent, "AdminCapstoneApplication class should be present in sources");
    }
}

