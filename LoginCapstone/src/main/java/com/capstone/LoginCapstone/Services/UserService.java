package com.capstone.LoginCapstone.Services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.LoginCapstone.Component.JwtUtil;
import com.capstone.LoginCapstone.Entity.User;
import com.capstone.LoginCapstone.Module.ChangePasswordRequest;
import com.capstone.LoginCapstone.Module.LoginRequest;
import com.capstone.LoginCapstone.Module.LoginResponseWithToken;
import com.capstone.LoginCapstone.Repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private EmailSenderService senderService;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public LoginResponseWithToken authenticateUser(String username, String password) throws Exception {
        // Business logic for authenticating user...
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()) {
            throw new Exception("User not found");
        }

        User user = optionalUser.get();
        
        if (user.isDeleted()) {
            throw new Exception("User account is deactivated");
        }
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new Exception("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponseWithToken(token, user.getUserid(), user.getUsername(), user.getRole(), user.isFirstTimeLogin());
    }
    
    public void updatePassword(String username, String newPassword) throws Exception {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found");
        }

        User user = optionalUser.get();
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(username, newPassword);
        updateNewPassword(user, changePasswordRequest.getNewPassword());
    }

    public void updateNewPassword(User user, String newPassword) {
        user.setPassword((passwordEncoder.encode(newPassword)));
        user.setFirstTimeLogin(false);// Make sure to hash the password before setting it
        userRepository.save(user);
    }
    
    public void sendPasswordResetLink(String email) throws Exception {
        Optional<User> userOptional = userRepository.findByEmailid(email);
        if (!userOptional.isPresent()) {
            throw new Exception("User not found");
        }

        User user = userOptional.get();
        String token = jwtUtil.generateToken(user.getUsername());
        String subject = "Password Reset Request";
        String resetLink = "http://localhost:3000/resetpass?token=" + token;
        String message = "<p>To reset your password, click the link below:</p>" +
                         "<p><a href=\"" + resetLink + "\">Reset Password</a></p>";

        senderService.sendEmail(email, subject, message);
    }
    
    public void resetPassword(String token, String newPassword) throws Exception {
        String username = jwtUtil.extractUsername(token);
        System.out.println(username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new Exception("User not found");
        }

        User user = userOptional.get();
        System.out.println(newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
