package com.example.userDetails.Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.userDetails.Repositories.ArtistRepository;
import com.example.userDetails.Repositories.ManagerRepository;
import com.example.userDetails.Repositories.UserDetailsDTO;
import com.example.userDetails.Repositories.UserRepository;
import com.example.userDetails.model.Artist;
import com.example.userDetails.model.Manager;
import com.example.userDetails.model.User;

public class UserDetailsServiceTest {

    @InjectMocks
    private UserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ManagerRepository managerRepository;

    private User user;
    private Artist artist;
    private Manager manager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setUserid(1L);
        user.setEmailid("test@example.com");

        artist = new Artist();
        artist.setArtistid(1L);

        manager = new Manager();
        manager.setManagerid(1L);
    }

    @Test
    public void testGetUserDetailsByUserId_UserFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
      //  when(artistRepository.findByUser(1L)).thenReturn(Optional.of(artist));
      //  when(managerRepository.findByUserId(1L)).thenReturn(Optional.of(manager));

        UserDetailsDTO userDetailsDTO = userDetailsService.getUserDetailsByUserId(1L);

        assertEquals(user, userDetailsDTO.getUser());
       // assertEquals(manager, userDetailsDTO.getManager());
    }

    @Test
    public void testGetUserDetailsByUserId_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserDetailsDTO userDetailsDTO = userDetailsService.getUserDetailsByUserId(1L);

        assertNull(userDetailsDTO);
    }

    
    @Test
    public void testUpdateArtistDetails_ArtistNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(artistRepository.findById(1L)).thenReturn(Optional.empty());

        Artist updatedArtist = userDetailsService.updateArtistDetails(1L, artist);

        assertNull(updatedArtist);
    }

    

    @Test
    public void testUpdateManagerDetails_ManagerNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(managerRepository.findById(1L)).thenReturn(Optional.empty());

        Manager updatedManager = userDetailsService.updateManagerDetails(1L, manager);

        assertNull(updatedManager);
    }

    @Test
    public void testUpdateUserEmail_UserFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userDetailsService.updateUserEmail(1L, "new-email@example.com");

        assertEquals("new-email@example.com", updatedUser.getEmailid());
    }

    @Test
    public void testUpdateUserEmail_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User updatedUser = userDetailsService.updateUserEmail(1L, "new-email@example.com");

        assertNull(updatedUser);
    }
}
