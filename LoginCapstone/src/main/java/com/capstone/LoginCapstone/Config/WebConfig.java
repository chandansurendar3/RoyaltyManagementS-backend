package com.capstone.LoginCapstone.Config;


//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
              .allowedOrigins("http://localhost:3000","https://royalty-management-system-kdrb.vercel.app/") // Add your client URL
              .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH")
              .allowedHeaders("*")
              .allowCredentials(true);
  }
}
