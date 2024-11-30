package com.capstone.AdminCapstone.Service;

import com.capstone.AdminCapstone.Entities.Artists;
import com.capstone.AdminCapstone.Entities.Managers;

import com.capstone.AdminCapstone.Entities.Role;
import com.capstone.AdminCapstone.Entities.User;
import com.capstone.AdminCapstone.Repository.ArtistRepository;
import com.capstone.AdminCapstone.Repository.ManagerRepository;
import com.capstone.AdminCapstone.Repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllActiveUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setRole("USER");
        user1.setDeleted(false);
        users.add(user1);

        User user2 = new User();
        user2.setRole("ADMIN");
        user2.setDeleted(false);
        users.add(user2);

        when(userRepository.findByIsDeletedFalse()).thenReturn(users);

        List<User> result = userService.getAllActiveUsers();

        assertEquals(1, result.size());
        assertEquals("USER", result.get(0).getRole());
    }

    @Test
    void testGetAllActiveUsersWhenNoUsers() {
        when(userRepository.findByIsDeletedFalse()).thenReturn(new ArrayList<>());

        List<User> result = userService.getAllActiveUsers();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllActiveUsersWhenAllAdmins() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setRole("ADMIN");
        user.setDeleted(false);
        users.add(user);

        when(userRepository.findByIsDeletedFalse()).thenReturn(users);

        List<User> result = userService.getAllActiveUsers();

        assertTrue(result.isEmpty());
    }

    @Test
    void testRegisterUser() {
        String username = "testUser";
        String password = "testPassword";
        String email = "test@example.com";
        String role = "USER";

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmailid(email);
        user.setRole(role);
        user.setFirstTimeLogin(true);

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        User result = userService.registerUser(username, password, email, role);

        assertEquals(username, result.getUsername());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(email, result.getEmailid());
        assertEquals(role, result.getRole());
        assertTrue(result.isFirstTimeLogin());
    }

    @Test
    @Transactional
    @DirtiesContext
    void testSoftDeleteUser() {
        Long userId = 1L;

        User user = new User();
        user.setUserid(userId);
        user.setDeleted(false);

        Artists artist = new Artists();
        artist.setArtistid(userId);
        artist.setDeleted(false);

        Managers manager = new Managers();
        manager.setManagerid(userId);
        manager.setDeleted(false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(artistRepository.findByArtistid(userId)).thenReturn(artist);
        when(managerRepository.findByManagerid(userId)).thenReturn(manager);

        userService.softDeleteUser(userId);

        assertTrue(user.isDeleted());
        assertTrue(artist.isDeleted());
        assertTrue(manager.isDeleted());

        verify(userRepository, times(1)).save(user);
        verify(artistRepository, times(1)).save(artist);
        verify(managerRepository, times(1)).save(manager);
    }

    @Test
    void testSoftDeleteUserWhenUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            userService.softDeleteUser(userId);
        });

        assertEquals("User not found with id: " + userId, exception.getMessage());
    }
}

