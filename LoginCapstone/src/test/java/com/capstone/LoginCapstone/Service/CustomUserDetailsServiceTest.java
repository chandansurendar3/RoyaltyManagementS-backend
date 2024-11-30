package com.capstone.LoginCapstone.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capstone.LoginCapstone.Entity.User;
import com.capstone.LoginCapstone.Repository.UserRepository;
import com.capstone.LoginCapstone.Services.CustomUserDetailsService;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    // No need for @BeforeEach setUp method as MockitoExtension takes care of it

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("testuser");
        });
    }

    @Test
    public void testLoadUserByUsername_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setRole("USER");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser");

        assertEquals("testuser", userDetails.getUsername());
        assertEquals("testpassword", userDetails.getPassword());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }
}
