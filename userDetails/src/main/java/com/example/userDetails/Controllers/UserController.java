package com.example.userDetails.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userDetails.Repositories.UserDetailsDTO;
import com.example.userDetails.Service.UserDetailsService;
import com.example.userDetails.model.Artist;
import com.example.userDetails.model.Manager;
import com.example.userDetails.model.User;
import java.util.*;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/api/user-details")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserDetailsByUserId(@PathVariable Long userId) {
    	System.out.println(userId);
        UserDetailsDTO userDetails = userDetailsService.getUserDetailsByUserId(userId);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/artists/{userId}")
    public ResponseEntity<Artist> updateArtistDetails(@PathVariable Long userId, @RequestBody Artist artistDetails) {
        Artist updatedArtist = userDetailsService.updateArtistDetails(userId, artistDetails);
        if (updatedArtist != null) {
            return ResponseEntity.ok(updatedArtist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/managers/{userId}")
    public ResponseEntity<Manager> updateManagerDetails(@PathVariable Long userId, @RequestBody Manager managerDetails) {
        Manager updatedManager = userDetailsService.updateManagerDetails(userId, managerDetails);
        if (updatedManager != null) {
            return ResponseEntity.ok(updatedManager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<User> updateUserEmail(@PathVariable Long userId, @RequestBody User userDetails) {
        User updatedUser = userDetailsService.updateUserEmail(userId, userDetails.getEmailid());
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/artists-by-manager/{managerId}")
    public ResponseEntity<List<Long>> getArtistsByManagerId(@PathVariable Long managerid) {
        List<Long> artists = userDetailsService.getArtistsByManagerid(managerid);
        if (artists != null && !artists.isEmpty()) {
            return new ResponseEntity<>(artists, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("getArtists/{managerId}")
    public ResponseEntity<?> getArtistDetailsByUserId(@PathVariable Long managerId) {
       List<Long> artists = userDetailsService.getArtistsByManagerid(managerId);
	return new ResponseEntity<>(artists, HttpStatus.OK);
    }
}

