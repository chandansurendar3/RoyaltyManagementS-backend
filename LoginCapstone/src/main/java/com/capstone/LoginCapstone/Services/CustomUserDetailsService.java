package com.capstone.LoginCapstone.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.LoginCapstone.Entity.User;
import com.capstone.LoginCapstone.Repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	 	@Autowired
	    private UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<User> userOptional = userRepository.findByUsername(username);
	        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUsername())
	                .password(user.getPassword())
	                .roles(user.getRole())
	                .build();
	    }

}
