package com.capstone.AdminCapstone.Controller;

import com.capstone.AdminCapstone.Entities.Artists;
import com.capstone.AdminCapstone.Entities.Managers;

import com.capstone.AdminCapstone.Entities.Role;
import com.capstone.AdminCapstone.Entities.User;
import com.capstone.AdminCapstone.Model.ArtistRegistrationRequest;
import com.capstone.AdminCapstone.Model.ManagerRegisterRequest;
import com.capstone.AdminCapstone.Repository.UserRepository;
import com.capstone.AdminCapstone.Service.ArtistService;
import com.capstone.AdminCapstone.Service.EmailSenderService;
import com.capstone.AdminCapstone.Service.ManagerService;
import com.capstone.AdminCapstone.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ArtistService artistService;

    @MockBean
    private ManagerService managerService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EmailSenderService senderService;

    private ArtistRegistrationRequest artistRequest;
    private ManagerRegisterRequest managerRequest;

    @BeforeEach
    public void setup() {
        artistRequest = new ArtistRegistrationRequest();
        artistRequest.setUsername("artistUser");
        artistRequest.setPassword("artistPass");
        artistRequest.setEmailid("artist@example.com");
        artistRequest.setPhoneNo("1234567890");
        artistRequest.setStageName("Artist Stage");
        artistRequest.setRealName("Artist Real");
        artistRequest.setCountry("Country");

        managerRequest = new ManagerRegisterRequest();
        managerRequest.setUsername("managerUser");
        managerRequest.setPassword("managerPass");
        managerRequest.setEmailid("manager@example.com");
        managerRequest.setManagerName("Manager Name");
        managerRequest.setCompany("Company");
    }

    @Test
    public void testRegisterArtist_Success() throws Exception {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmailid(anyString())).thenReturn(false);
        
        User newUser = new User();
        newUser.setUserid(1L);
        newUser.setUsername("artistUser");
        newUser.setPassword("artistPass");
        newUser.setEmailid("artist@example.com");
        newUser.setRole("ARTIST");
        newUser.setFirstTimeLogin(true);
        
        when(userService.registerUser(anyString(), anyString(), anyString(), anyString())).thenReturn(newUser);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(newUser));
        
        mockMvc.perform(post("/user/register/artist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"artistUser\", \"password\": \"artistPass\", \"emailid\": \"artist@example.com\", \"phoneNo\": \"1234567890\", \"stageName\": \"Artist Stage\", \"realName\": \"Artist Real\", \"country\": \"Country\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testRegisterManager_Success() throws Exception {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmailid(anyString())).thenReturn(false);
        
        User newUser = new User();
        newUser.setUserid(2L);
        newUser.setUsername("managerUser");
        newUser.setPassword("managerPass");
        newUser.setEmailid("manager@example.com");
        newUser.setRole("MANAGER");
        newUser.setFirstTimeLogin(true);
        
        when(userService.registerUser(anyString(), anyString(), anyString(), anyString())).thenReturn(newUser);
        when(userRepository.findById(2L)).thenReturn(java.util.Optional.of(newUser));

        mockMvc.perform(post("/user/register/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"managerUser\", \"password\": \"managerPass\", \"emailid\": \"manager@example.com\", \"managerName\": \"Manager Name\", \"company\": \"Company\" }"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testRegisterArtist_UsernameExists() throws Exception {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        mockMvc.perform(post("/user/register/artist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"artistUser\", \"password\": \"artistPass\", \"emailid\": \"artist@example.com\", \"phoneNo\": \"1234567890\", \"stageName\": \"Artist Stage\", \"realName\": \"Artist Real\", \"country\": \"Country\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username already exists"));
    }

    @Test
    public void testRegisterManager_EmailExists() throws Exception {
        when(userRepository.existsByEmailid(anyString())).thenReturn(true);

        mockMvc.perform(post("/user/register/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"managerUser\", \"password\": \"managerPass\", \"emailid\": \"manager@example.com\", \"managerName\": \"Manager Name\", \"company\": \"Company\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists"));
    }
    @Test
    public void testRegisterArtist_InternalServerError() throws Exception {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmailid(anyString())).thenReturn(false);
        when(userService.registerUser(anyString(), anyString(), anyString(), anyString())).thenThrow(new RuntimeException());

        mockMvc.perform(post("/user/register/artist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"artistUser\", \"password\": \"artistPass\", \"emailid\": \"artist@example.com\", \"phoneNo\": \"1234567890\", \"stageName\": \"Artist Stage\", \"realName\": \"Artist Real\", \"country\": \"Country\" }"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Registration failed"));
    }

    @Test
    public void testDeleteUser_Success() throws Exception {
        mockMvc.perform(delete("/user/register/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User and associated records deleted successfully"));
    }

    @Test
    public void testDeleteUser_Failure() throws Exception {
        Mockito.doThrow(new RuntimeException()).when(userService).softDeleteUser(1L);

        mockMvc.perform(delete("/user/register/delete/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to delete user and associated records"));
    }

    @Test
    public void testGetAllActiveUsers_Success() throws Exception {
        List<User> activeUsers = new ArrayList<>();
        User user1 = new User();
        user1.setUserid(1L);
        user1.setUsername("user1");
        user1.setEmailid("user1@example.com");
        activeUsers.add(user1);

        when(userService.getAllActiveUsers()).thenReturn(activeUsers);

        mockMvc.perform(get("/user/register/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"));
    }
    @Test
    public void testDeleteUser_Success1() throws Exception {
        // Mock successful deletion
        mockMvc.perform(delete("/user/register/delet/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User and associated records deleted successfully"));
    }

    @Test
    public void testDeleteUser_Failure1() throws Exception {
        // Mock failure to delete
        Mockito.doThrow(new RuntimeException()).when(userService).softDeleteUser(1L);

        mockMvc.perform(delete("/user/register/delet/1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to delete user and associated records"));
    }

}
