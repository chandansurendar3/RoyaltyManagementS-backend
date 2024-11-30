package com.capstone.AdminCapstone.Service;

import com.capstone.AdminCapstone.Entities.Role;
import com.capstone.AdminCapstone.Entities.Managers;
import com.capstone.AdminCapstone.Repository.ManagerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ManagerService managerService;

    @Test
    void testSaveManager() {
        // Given
        Managers manager = new Managers();
        manager.setManagerid(1L);
        manager.setManagerName("Test Manager");
        manager.setCompany("Test Company");

        // When
        managerService.saveManger(manager);

        // Then
        verify(managerRepository, times(1)).save(manager);
    }
}


