//package com.RoyalityManagement.ArtistRequest.Controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import com.RoyalityManagement.ArtistRequest.Entity.Contract;
//import com.RoyalityManagement.ArtistRequest.Repo.ContractRepository;
//import com.RoyalityManagement.ArtistRequest.Service.ContractService;
//
//public class ContractControllerTest {
//
//    @Mock
//    private ContractService contractService;
//
//    @Mock
//    private ContractRepository contractRepository;
//
//    @InjectMocks
//    private ContarctController contractController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSaveContract() {
//        Contract contract = new Contract();
//        when(contractService.saveContract(contract)).thenReturn(contract);
//        assertEquals(contract, contractController.saveContract(contract));
//    }
//
//    @Test
//    public void testGetPendingContracts() {
//        Contract contract = new Contract();
//        when(contractService.getContractsByManagerId(1L)).thenReturn(Arrays.asList(contract));
//        ResponseEntity<List<Contract>> response = contractController.getPendingContracts(1L);
//        assertEquals(1, response.getBody().size());
//    }
//
//    @Test
//    public void testUpdateContract() {
//        Contract contract = new Contract();
//        contract.setFlag(false);
//
//        when(contractRepository.findById(1L)).thenReturn(Optional.of(contract));
//        when(contractRepository.save(contract)).thenReturn(contract);
//
//        Map<String, Boolean> updates = new HashMap<>();
//        updates.put("flag", true);
//
//        ResponseEntity<Contract> response = contractController.updateContract(1L, updates);
//
//        assertTrue(contract.getFlag());
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
////    @Test
////    public void testUpdateContractNotFound() {
////        when(contractRepository.findById(1L)).thenReturn(Optional.empty());
////
////        Map<String, Boolean> updates = new HashMap<>();
////        updates.put("flag", true);
////
////        ResponseEntity<Contract> response = contractController.updateContract(1L, updates);
////
////        assertEquals(404, response.getStatusCodeValue());
////    }
//
//    @Test
//    public void testDeleteContract() {
//        doNothing().when(contractService).deleteContract(1L);
//
//        ResponseEntity<String> response = contractController.deleteContract(1L);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("Contract deleted successfully.", response.getBody());
//    }
//
//    @Test
//    public void testDeleteContractNotFound() {
//        doThrow(new RuntimeException("Contract not found")).when(contractService).deleteContract(1L);
//
//        ResponseEntity<String> response = contractController.deleteContract(1L);
//
//        assertEquals(404, response.getStatusCodeValue());
//        assertEquals("Contract not found.", response.getBody());
//    }
//}
//
