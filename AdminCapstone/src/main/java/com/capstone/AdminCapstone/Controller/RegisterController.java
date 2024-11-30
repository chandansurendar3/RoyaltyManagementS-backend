package com.capstone.AdminCapstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.AdminCapstone.Entities.Artists;
import com.capstone.AdminCapstone.Entities.Managers;
import com.capstone.AdminCapstone.Entities.User;
import com.capstone.AdminCapstone.Model.ArtistRegistrationRequest;
import com.capstone.AdminCapstone.Model.ManagerRegisterRequest;
import com.capstone.AdminCapstone.Repository.UserRepository;
import com.capstone.AdminCapstone.Service.ArtistService;
import com.capstone.AdminCapstone.Service.EmailSenderService;
import com.capstone.AdminCapstone.Service.ManagerService;
import com.capstone.AdminCapstone.Service.UserService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(value = {"http://localhost:3000/","https://royalty-management-system-kdrb.vercel.app/"})
@RequestMapping("/user/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailSenderService senderService;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllActiveUsers() {
        List<User> activeUsers = userService.getAllActiveUsers();
        return ResponseEntity.ok(activeUsers);
    }

    @PostMapping("/artist")
    public ResponseEntity<?> registerArtist(@RequestBody ArtistRegistrationRequest a_request) {
    	
    	System.out.println(a_request);
        try {
            if (userRepository.existsByUsername(a_request.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            } else if (userRepository.existsByEmailid(a_request.getEmailid())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }

            // Register user
            User newUser = userService.registerUser(a_request.getUsername(), a_request.getPassword(), a_request.getEmailid(), "ARTIST");

            // Fetch the User object from the database based on the provided userid
            User user = userRepository.findById(newUser.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + newUser.getUserid()));

            // Create a new Artist object
            System.out.println(user.getUserid());
            Artists artist = new Artists();
            artist.setArtistid(user.getUserid()); // Set the artistid to the userid
            artist.setPhoneNo(a_request.getPhoneNo());
            artist.setStageName(a_request.getStageName());
            artist.setRealName(a_request.getRealName());
            artist.setCountry(a_request.getCountry());

            // Save the Artist entity
         // Save the Artist entity
            artistService.saveArtist(artist);

            // Send email using senderService
            senderService.sendEmail(a_request.getEmailid(), "New Registration Details", "UserName: "+a_request.getUsername()+"Password: " + a_request.getPassword());


            return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
    
    @PostMapping("/manager")
    public ResponseEntity<?> registerManager(@RequestBody ManagerRegisterRequest m_request) {
        try {
            if (userRepository.existsByUsername(m_request.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            } else if (userRepository.existsByEmailid(m_request.getEmailid())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }

            // Register user
            User newUser = userService.registerUser(m_request.getUsername(), m_request.getPassword(), m_request.getEmailid(), "MANAGER");

            // Fetch the User object from the database based on the provided userid
            User user = userRepository.findById(newUser.getUserid())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + newUser.getUserid()));

            // Create a new Artist object
            Managers manager = new Managers();
//            artist.setUser(user); // Set the User object to the artist
            manager.setManagerid(user.getUserid()); // Set the artistid to the userid
            manager.setManagerName(m_request.getManagerName());
            manager.setCompany(m_request.getCompany());
            

            // Save the Artist entity
            managerService.saveManger(manager);
            
            senderService.sendEmail(m_request.getEmailid(), "New Registration Details", "UserName: "+m_request.getUsername()+"Password: " + m_request.getPassword());

            return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
    
    @DeleteMapping("/delet/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.softDeleteUser(userId);
            return ResponseEntity.ok("User and associated records deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user and associated records");
        }
    }
}
