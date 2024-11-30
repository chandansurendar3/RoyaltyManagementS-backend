package com.capstone.AdminCapstone.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.AdminCapstone.Entities.Artists;
import com.capstone.AdminCapstone.Entities.Managers;
import com.capstone.AdminCapstone.Entities.User;
import com.capstone.AdminCapstone.Repository.ArtistRepository;
import com.capstone.AdminCapstone.Repository.ManagerRepository;
import com.capstone.AdminCapstone.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public List<User> getAllActiveUsers() {
        List<User> activeUsers = userRepository.findByIsDeletedFalse();
        
        // Filter out admin users
        List<User> nonAdminUsers = activeUsers.stream()
                .filter(user -> !"ADMIN".equals(user.getRole()))
                .collect(Collectors.toList());

        return nonAdminUsers;
    }

    @Transactional
    public User registerUser(String username, String password, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmailid(email);
        user.setRole(role);
        user.setFirstTimeLogin(true);
        return userRepository.save(user);
    }
    
    @Transactional
    public void softDeleteUser(Long userId) {
        // Soft delete user
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        user.setDeleted(true);
        userRepository.save(user);

        // Soft delete associated records
        Artists artist = artistRepository.findByArtistid(userId);
        if (artist != null) {
            artist.setDeleted(true);
            artistRepository.save(artist);
        }

        Managers manager = managerRepository.findByManagerid(userId);
        if (manager != null) {
            // Set managerid to null for all artists associated with this manager
            List<Artists> artists = artistRepository.findByManagerid(manager.getManagerid());
            for (Artists associatedArtist : artists) {
                associatedArtist.setManagerid(null);
                artistRepository.save(associatedArtist);
            }
 
            // Soft delete the manager
            manager.setDeleted(true);
            managerRepository.save(manager);
        }
    }
 
}
