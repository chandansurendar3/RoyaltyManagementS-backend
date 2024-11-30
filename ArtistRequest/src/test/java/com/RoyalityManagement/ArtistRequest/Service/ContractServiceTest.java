package com.RoyalityManagement.ArtistRequest.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.RoyalityManagement.ArtistRequest.Entity.Contract;
import com.RoyalityManagement.ArtistRequest.Repo.ContractRepository;

public class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private ContractService contractService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveContract() {
        Contract contract = new Contract();
        when(contractRepository.save(contract)).thenReturn(contract);
        assertEquals(contract, contractService.saveContract(contract));
    }

    @Test
    public void testDeleteContract() {
        Contract contract = new Contract();
        when(contractRepository.findById(1L)).thenReturn(Optional.of(contract));
        contractService.deleteContract(1L);
        verify(contractRepository, times(1)).delete(contract);
    }

    @Test
    public void testGetContractsByManagerId() {
        Contract contract = new Contract();
        when(contractRepository.findContractsByManagerIdAndApproacedByArtist(1L)).thenReturn(Arrays.asList(contract));
        assertEquals(1, contractService.getContractsByManagerId(1L).size());
    }

    @Test
    public void testUpdateContractStatus() {
        Contract contract = new Contract();
        contract.setContractStartDate(LocalDate.of(2023, 1, 1));
        contract.setContractEndDate(LocalDate.of(2023, 12, 31));

        when(contractRepository.findActiveContracts(LocalDate.now())).thenReturn(Arrays.asList(contract));
        when(contractRepository.findAll()).thenReturn(Arrays.asList(contract));

        contractService.updateContractStatus();

        assertEquals("active", contract.getContractStatus());
    }
}
