package com.capstone.AlertCapstone;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServletInitializerTest {

    @Test
    void configure() {
        ServletInitializer servletInitializer = new ServletInitializer();
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AlertCapstoneApplication.class);
        SpringApplicationBuilder configuredBuilder = servletInitializer.configure(builder);
        assertNotNull(configuredBuilder);
    }
}
