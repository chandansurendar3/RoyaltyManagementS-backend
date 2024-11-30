
package com.RoyalityManagement.ArtistRequest.Controller;

import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RoyalityManagement.ArtistRequest.Entity.Artists;
import com.RoyalityManagement.ArtistRequest.Entity.Contract;
import com.RoyalityManagement.ArtistRequest.Entity.Managers;
import com.RoyalityManagement.ArtistRequest.Repo.ArtistRepository;
import com.RoyalityManagement.ArtistRequest.Repo.ContractRepository;
import com.RoyalityManagement.ArtistRequest.Service.ContractService;
import com.RoyalityManagement.ArtistRequest.Service.ManagerService;

@RestController
@RequestMapping("/contracts")
@CrossOrigin(origins = {"http://localhost:3000","https://royalty-management-system-kdrb.vercel.app/"})
public class ContarctController {
	
	
	 	@Autowired
	    private ContractService contractService;
	 	
	 	@Autowired
	 	private ContractRepository contractRepository;
	 	
	    @Autowired
	    private ManagerService managerService;
	    
	    @Autowired
	    private ArtistRepository artistRepository;
	    
	 	
	    //Fetching all the manager in manager table
	    @GetMapping("/managers/allManagers")
	    public ResponseEntity<List<Managers>> getAllManagers() {
	        List<Managers> managers = managerService.getAllManagers();
	        return ResponseEntity.ok(managers); // Return 200 OK with the list of managers
	    }
	 	
	 	
	 	//saving all to the contract table
	    @PostMapping("/save")
	    public Contract saveContract(@RequestBody Contract contract) {
	    	System.out.println(contract);
	        return contractService.saveContract(contract);
	    }
	    
	    
	    //Fetching from contract table all pending requests
	    @GetMapping("/pending/{managerId}")
	    
	    public ResponseEntity<List<Contract>> getPendingContracts(@PathVariable Long managerId) {
	    	
	    	List<Contract> contracts = contractService.getContractsByManagerId(managerId);
	    	System.out.println(contracts);
	        return ResponseEntity.ok(contracts);
	    }
	    
	    
	    //Updating managerid in the artist  table
		@PutMapping("/artists/{artistId}")
	    public ResponseEntity<Artists> updateArtist(@PathVariable Long artistId, @RequestBody Map<String, Long> updates) {
	        return artistRepository.findById(artistId)
	                .map(artist -> {
	                    Long managerId = updates.get("managerId");
	                    if (managerId != null) {
	                        artist.setManager_id(managerId);
	                        artistRepository.save(artist);
	                    }
	                    return ResponseEntity.ok(artist);
	                }).orElse(ResponseEntity.notFound().build());
	    }
	    
	    
	    
	    //when accepted clicked changing status 
	    @PutMapping("/{contractId}")
	    public ResponseEntity<Contract> updateContract(@PathVariable Long contractId, @RequestBody Map<String, String> updates) {
	        return contractRepository.findById(contractId)
	                .map(contract -> {
	                    String status = updates.get("status");
	                    if (status != null) {
	                        contract.setStatus(status);
	                        contractRepository.save(contract);
	                    }
	                    return ResponseEntity.ok(contract);
	                }).orElse(ResponseEntity.notFound().build());
	    }
	    
	    //when clicked rejected deleting the data from contract
	    @DeleteMapping("/{contractId}")
	    public ResponseEntity<String> deleteContract(@PathVariable Long contractId) {
	        try {
	            contractService.deleteContract(contractId);            
	            return ResponseEntity.ok("Contract deleted successfully.");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Contract not found.");
	        }
	    }
	   
	}
	    
	    
	    

