package com.example.userDetails.Controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.userDetails.Controllers.UserController;
import com.example.userDetails.Repositories.UserDetailsDTO;
import com.example.userDetails.Service.UserDetailsService;
import com.example.userDetails.model.Artist;
import com.example.userDetails.model.Manager;
import com.example.userDetails.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private UserDetailsDTO userDetailsDTO;
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

        userDetailsDTO = new UserDetailsDTO(user, artist, manager, "artist");
    }

    @Test
    public void testGetUserDetailsByUserId() throws Exception {
        when(userDetailsService.getUserDetailsByUserId(1L)).thenReturn(userDetailsDTO);

        mockMvc.perform(get("/api/user-details/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(userDetailsDTO)));
    }

    @Test
    public void testGetUserDetailsByUserIdNotFound() throws Exception {
        when(userDetailsService.getUserDetailsByUserId(1L)).thenReturn(null);

        mockMvc.perform(get("/api/user-details/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateArtistDetails() throws Exception {
        Artist updatedArtist = new Artist();
        updatedArtist.setArtistid(1L);

        when(userDetailsService.updateArtistDetails(eq(1L), any(Artist.class))).thenReturn(updatedArtist);

        mockMvc.perform(put("/api/user-details/artists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artist)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(updatedArtist)));
    }

    @Test
    public void testUpdateArtistDetailsNotFound() throws Exception {
        when(userDetailsService.updateArtistDetails(eq(1L), any(Artist.class))).thenReturn(null);

        mockMvc.perform(put("/api/user-details/artists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artist)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateManagerDetails() throws Exception {
        Manager updatedManager = new Manager();
        updatedManager.setManagerid(1L);

        when(userDetailsService.updateManagerDetails(eq(1L), any(Manager.class))).thenReturn(updatedManager);

        mockMvc.perform(put("/api/user-details/managers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(manager)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(updatedManager)));
    }

    @Test
    public void testUpdateManagerDetailsNotFound() throws Exception {
        when(userDetailsService.updateManagerDetails(eq(1L), any(Manager.class))).thenReturn(null);

        mockMvc.perform(put("/api/user-details/managers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(manager)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUserEmail() throws Exception {
        User updatedUser = new User();
        updatedUser.setUserid(1L);
        updatedUser.setEmailid("new-email@example.com");

        when(userDetailsService.updateUserEmail(eq(1L), any(String.class))).thenReturn(updatedUser);

        user.setEmailid("new-email@example.com");

        mockMvc.perform(put("/api/user-details/1/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(updatedUser)));
    }

    @Test
    public void testUpdateUserEmailNotFound() throws Exception {
        when(userDetailsService.updateUserEmail(eq(1L), any(String.class))).thenReturn(null);

        user.setEmailid("new-email@example.com");

        mockMvc.perform(put("/api/user-details/1/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNotFound());
    }
}
