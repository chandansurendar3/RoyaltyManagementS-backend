package com.RoyaltyManagement.ManagerRequest.Controller;

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

import com.RoyaltyManagement.ManagerRequest.Entity.Artists;
import com.RoyaltyManagement.ManagerRequest.Entity.Contract;
import com.RoyaltyManagement.ManagerRequest.Repo.ArtistRepository;
import com.RoyaltyManagement.ManagerRequest.Repo.ContractRepository;
import com.RoyaltyManagement.ManagerRequest.Repo.ManagerRepository;
import com.RoyaltyManagement.ManagerRequest.Service.ArtistService;
import com.RoyaltyManagement.ManagerRequest.Service.ContractService;

@RestController
@RequestMapping("/contracts")
@CrossOrigin(origins = {"http://localhost:3000","https://royalty-management-system-kdrb.vercel.app/"})
public class ContractController {
	
	
	 	@Autowired
	    private ContractService contractService;
	 	
	 	@Autowired
	 	private ContractRepository contractRepository;
	 	
	    @Autowired
	    private ArtistService artistService;
	    
	    @Autowired
	    private ArtistRepository artistRepository;
	    
	    @Autowired
	    private ManagerRepository managerRepository;

	    //Fetching all the manager in manager table
	    @GetMapping("/artists/nullManagerId")
	    public ResponseEntity<List<Artists>> getArtistsWithNullManagerId() {
	        List<Artists> artists = artistService.getArtistsWithNullManagerId();
	        System.out.println(artists);
	        return ResponseEntity.ok(artists);
	    }
 
	  //saving all to the contract table
	    @PostMapping("/save")
	    public Contract saveContract(@RequestBody Contract contract) {
	    	System.out.println(contract);
	        return contractService.saveContract(contract);
	    }
	    
	    //Fetching from contract table all pending requests
	    @GetMapping("/pending/{artistId}")
	    public ResponseEntity<List<Contract>> getPendingContracts(@PathVariable Long artistId) {
	    	List<Contract> contracts = contractService.getContractsByArtistId(artistId);
	        return ResponseEntity.ok(contracts);
	    }
	    
	    //Updating managerid in the artist  table
	    @PostMapping("/artists/{artistId}")
	    public ResponseEntity<Artists> updateArtist(@PathVariable Long artistId, @RequestBody Map<String, Long> updates) {
	    	System.out.println(artistId);
	        return artistRepository.findById(artistId)
	                .map(artist -> {
	                    Long managerId = updates.get("managerId");
	                    if (managerId != null) {
	                        artist.setManagerId(managerId);
	                        artistRepository.save(artist);
	                    }
	                    return ResponseEntity.ok(artist);
	                }).orElse(ResponseEntity.notFound().build());
	    }
	    
	    //when accepted clicked changing status 
	    @PostMapping("/{contractId}")
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
	    
	    
	    

