package com.capstone.LoginCapstone.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capstone.LoginCapstone.Security.JwtRequestFilter;
import com.capstone.LoginCapstone.Services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

		@Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Autowired
	    private JwtRequestFilter jwtRequestFilter;

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	http.csrf()
	    		.disable()
            	.authorizeHttpRequests()
                .requestMatchers(("/user/login"), ("/user/change"), ("/user/forgot-password"), ("/user/reset-password"))
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
	    
	    
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
