package com.capstone.LoginCapstone.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capstone.LoginCapstone.Component.JwtUtil;
import com.capstone.LoginCapstone.Entity.User;
import com.capstone.LoginCapstone.Module.LoginResponseWithToken;
import com.capstone.LoginCapstone.Repository.UserRepository;
import com.capstone.LoginCapstone.Services.EmailSenderService;
import com.capstone.LoginCapstone.Services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private EmailSenderService emailSenderService;

    // No need for @BeforeEach setUp method as MockitoExtension takes care of it
    
    @Test
    void testFindByUsername_UserFound() {
        // Given
        String username = "testuser";
        User user = new User(); // Assuming default constructor exists
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // When
        Optional<User> foundUser = userService.findByUsername(username);

        // Then
        assertTrue(foundUser.isPresent()); // Verify user is found
        assertEquals(username, foundUser.get().getUsername()); // Verify correct user is returned
    }

    @Test
    void testFindByUsername_UserNotFound() {
        // Given
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When
        Optional<User> foundUser = userService.findByUsername(username);

        // Then
        assertFalse(foundUser.isPresent()); // Verify no user is found
    }

    @Test
    public void testAuthenticateUser_UserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            userService.authenticateUser("testuser", "testpassword");
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testAuthenticateUser_InvalidPassword() {
        User user = new User();
        user.setPassword("encodedPassword");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> {
            userService.authenticateUser("testuser", "testpassword");
        });

        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    public void testAuthenticateUser_Success() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
        user.setUserid(1L);
        user.setRole("USER");
        user.setFirstTimeLogin(false);

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtUtil.generateToken(anyString())).thenReturn("testtoken");

        LoginResponseWithToken response = userService.authenticateUser("testuser", "testpassword");

        assertEquals("testtoken", response.getToken());
        assertEquals(1L, response.getUserid());
        assertEquals("testuser", response.getUsername());
        assertEquals("USER", response.getRole());
        assertEquals(false, response.isFirstTimeLogin());
    }

    // Additional tests for other methods in UserService...
    
    @Test
    void testUpdatePassword_UserNotFound() {
        // Given
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When
        assertThrows(Exception.class, () -> userService.updatePassword(username, "newPassword"));

        // Then - Exception expected, no need for further verification
    }

    @Test
    void testUpdatePassword_Success() throws Exception {
        // Given
        String username = "testuser";
        User user = new User(); // Assuming default constructor exists
        user.setUsername(username);
        user.setPassword("oldPassword");
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        // When
        userService.updatePassword(username, "newPassword");

        // Then
        verify(passwordEncoder).encode("newPassword"); // Verifying if password was encoded
        assertEquals("hashedPassword", user.getPassword()); // Verifying if user's password was updated
        verify(userRepository).save(user); // Verifying if user was saved
    }

    @Test
    public void testSendPasswordResetLink_UserNotFound() {
        when(userRepository.findByEmailid(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            userService.sendPasswordResetLink("testemail@example.com");
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testSendPasswordResetLink_Success() throws Exception {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByEmailid(anyString())).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(anyString())).thenReturn("testtoken");

        userService.sendPasswordResetLink("testemail@example.com");

        verify(emailSenderService, times(1)).sendEmail(eq("testemail@example.com"), eq("Password Reset Request"), anyString());
    }
    
    @Test
    void testResetPassword_UserNotFound() {
        // Given
        String token = "invalidToken";
        when(jwtUtil.extractUsername(token)).thenReturn("nonExistentUser");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // When, Then
        assertThrows(Exception.class, () -> userService.resetPassword(token, "newPassword"));
    }

    @Test
    void testResetPassword_Success() throws Exception {
        // Given
        String token = "validToken";
        String newPassword = "newPassword";
        String username = "testuser";
        User user = new User(); // Assuming default constructor exists
        user.setUsername(username);
        when(jwtUtil.extractUsername(token)).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(newPassword)).thenReturn("hashedPassword");

        // When
        userService.resetPassword(token, newPassword);

        // Then
        verify(passwordEncoder).encode(newPassword); // Verifying if password was encoded
        assertEquals("hashedPassword", user.getPassword()); // Verifying if user's password was updated
        verify(userRepository).save(user); // Verifying if user was saved
    }
}
