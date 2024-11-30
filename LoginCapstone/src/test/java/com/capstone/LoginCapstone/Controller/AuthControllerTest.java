package com.capstone.LoginCapstone.Controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.LoginCapstone.Module.LoginResponseWithToken;
import com.capstone.LoginCapstone.Services.UserService;



@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testLogin() throws Exception {
        // Mocking request data
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "testuser");
        loginRequest.put("password", "testpassword");

        // Mocking response data
        LoginResponseWithToken response = new LoginResponseWithToken("valid-token", 1L, "testuser", "USER", false);
        when(userService.authenticateUser("testuser", "testpassword")).thenReturn(response);

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.login(loginRequest);

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(response, actualResponse.getBody());
    }

    @Test
    public void testLoginUserNotFound() throws Exception {
        // Mocking request data
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "nonexistentuser");
        loginRequest.put("password", "testpassword");

        // Mocking service method call to throw "User not found" exception
        when(userService.authenticateUser(anyString(), anyString())).thenThrow(new Exception("User not found"));

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.login(loginRequest);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
        assertEquals("User not found", actualResponse.getBody());
    }

    @Test
    public void testLoginInvalidPassword() throws Exception {
        // Mocking request data
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "testuser");
        loginRequest.put("password", "invalidpassword");

        // Mocking service method call to throw "Invalid password" exception
        when(userService.authenticateUser(anyString(), anyString())).thenThrow(new Exception("Invalid password"));

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.login(loginRequest);

        // Assertion
        assertEquals(HttpStatus.UNAUTHORIZED, actualResponse.getStatusCode());
        assertEquals("Invalid password", actualResponse.getBody());
    }

    @Test
    public void testLoginInternalServerError() throws Exception {
        // Mocking request data
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "testuser");
        loginRequest.put("password", "testpassword");

        // Mocking service method call to throw a generic exception
        when(userService.authenticateUser(anyString(), anyString())).thenThrow(new RuntimeException());

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.login(loginRequest);

        // Assertion
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertEquals("Internal server error", actualResponse.getBody());
    }

    @Test
    public void testChangePassword() {
        // Mocking request data
        Map<String, String> changePasswordRequest = new HashMap<>();
        changePasswordRequest.put("username", "testuser");
        changePasswordRequest.put("newPassword", "newpassword");

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.changePassword(changePasswordRequest);

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Password changed successfully", actualResponse.getBody());
    }

    @Test
    public void testChangePasswordUserNotFound() throws Exception {
        // Mocking request data
        Map<String, String> changePasswordRequest = new HashMap<>();
        changePasswordRequest.put("username", "nonexistentuser");
        changePasswordRequest.put("newPassword", "newpassword");

        // Mocking service method call to throw "User not found" exception
        doThrow(new Exception("User not found")).when(userService).updatePassword(anyString(), anyString());

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.changePassword(changePasswordRequest);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
        assertEquals("User not found", actualResponse.getBody());
    }

    @Test
    public void testForgotPassword() {
        // Mocking request data
        Map<String, String> forgotPasswordRequest = new HashMap<>();
        forgotPasswordRequest.put("email", "test@example.com");

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.forgotPassword(forgotPasswordRequest);

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Password reset link sent to your email", actualResponse.getBody());
    }

    @Test
    public void testForgotPasswordInternalServerError() throws Exception {
        // Mocking request data
        Map<String, String> forgotPasswordRequest = new HashMap<>();
        forgotPasswordRequest.put("email", "test@example.com");

        // Mocking service method call to throw a generic exception
        doThrow(new RuntimeException()).when(userService).sendPasswordResetLink(anyString());

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.forgotPassword(forgotPasswordRequest);

        // Assertion
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertEquals("Failed to send password reset link", actualResponse.getBody());
    }

    @Test
    public void testResetPassword() {
        // Mocking request data
        Map<String, String> resetPasswordRequest = new HashMap<>();
        resetPasswordRequest.put("token", "valid-token");
        resetPasswordRequest.put("newPassword", "newpassword");

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.resetPassword(resetPasswordRequest);

        // Assertion
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals("Password reset successfully", actualResponse.getBody());
    }

    @Test
    public void testResetPasswordInternalServerError() throws Exception {
        // Mocking request data
        Map<String, String> resetPasswordRequest = new HashMap<>();
        resetPasswordRequest.put("token", "valid-token");
        resetPasswordRequest.put("newPassword", "newpassword");

        // Mocking service method call to throw a generic exception
        doThrow(new RuntimeException()).when(userService).resetPassword(anyString(), anyString());

        // Testing controller method
        ResponseEntity<?> actualResponse = authController.resetPassword(resetPasswordRequest);

        // Assertion
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
        assertEquals("Error resetting password", actualResponse.getBody());
    }
}


