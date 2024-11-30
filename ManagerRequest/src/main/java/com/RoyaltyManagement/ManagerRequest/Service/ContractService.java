package com.RoyaltyManagement.ManagerRequest.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RoyaltyManagement.ManagerRequest.APPLICATION_CONSTANT;
import com.RoyaltyManagement.ManagerRequest.Entity.Contract;
import com.RoyaltyManagement.ManagerRequest.Repo.ContractRepository;

@Service
public class ContractService {
 
    @Autowired
    private ContractRepository contractRepository;
 
    //Saves the contract
    public Contract saveContract(Contract contract) {
    	contract.setStatus(APPLICATION_CONSTANT.PENDING);
        return contractRepository.save(contract);
    }
    
    //gets pending contracts
    public List<Contract> getContractsByArtistId(Long artistId) {        
        return contractRepository.findContractsByArtistIdAndApproacedByManager(artistId);     
    }

    //deletes the contract from table
    public void deleteContract(Long contractId) {         
    	contractRepository.findById(contractId)                 
    	.ifPresent(contractRepository::delete);     
    	}
    
    //Gives active or inactive status- 
    public void updateContractStatus() {
        LocalDate currentDate = LocalDate.now();
        List<Contract> contracts = contractRepository.findActiveContracts(currentDate);
 
        for (Contract contract : contracts) {
            contract.setContractStatus("active");
            contractRepository.save(contract);
        }
 
        List<Contract> allContracts = contractRepository.findAll();
        for (Contract contract : allContracts) {
            if (!contracts.contains(contract)) {
                contract.setContractStatus("inactive");
                contractRepository.save(contract);
            }
        }
    }
}
