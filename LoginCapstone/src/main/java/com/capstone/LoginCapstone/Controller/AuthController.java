package com.capstone.LoginCapstone.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.LoginCapstone.Module.LoginResponseWithToken;
import com.capstone.LoginCapstone.Services.UserService;



@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping("/user")
public class AuthController {

    
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequestMap) {
        String username = loginRequestMap.get("username");
        String password = loginRequestMap.get("password");

        try {
            LoginResponseWithToken response = userService.authenticateUser(username, password);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	String message = e.getMessage();
        	if ("User not found".equals(message)) {
        	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        	} else if ("Invalid password".equals(message)) {
        	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        	} 	else if ("User account is deactivated".equals(message)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
            }else {
        	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        	}
        }
    }

    @PatchMapping("/change")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> changePasswordData) {
        String username = changePasswordData.get("username");
        String newPassword = changePasswordData.get("newPassword");

        try {
            userService.updatePassword(username, newPassword);
            return ResponseEntity.ok("Password changed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    
    @PatchMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> forgotPasswordData) {
        String email = forgotPasswordData.get("email");
        try {
            userService.sendPasswordResetLink(email);
            return ResponseEntity.ok("Password reset link sent to your email");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send password reset link");
        }
    }
    
    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> resetPasswordData) {
        String token = resetPasswordData.get("token");
        System.out.println(token);
        String newPassword = resetPasswordData.get("password");
        System.out.println(newPassword);
        try {
            userService.resetPassword(token, newPassword);
            return ResponseEntity.ok("Password reset successfully");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resetting password");
        }
    }

}

