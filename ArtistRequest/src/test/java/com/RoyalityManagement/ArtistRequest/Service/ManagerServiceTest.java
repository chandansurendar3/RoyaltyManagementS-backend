package com.RoyalityManagement.ArtistRequest.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.RoyalityManagement.ArtistRequest.Entity.Managers;
import com.RoyalityManagement.ArtistRequest.Repo.ManagerRepository;

public class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ManagerService managerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllManagers() {
        Managers manager = new Managers();
        when(managerRepository.findAll()).thenReturn(Arrays.asList(manager));
        assertEquals(1, managerService.getAllManagers().size());
    }
}
