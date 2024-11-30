package com.RoyalityManagement.ArtistRequest.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.RoyalityManagement.ArtistRequest.Entity.Contract;

public interface ContractRepository extends JpaRepository<Contract,Long>{
	List<Contract> findByManagerId(Long managerId);
	
	@Query("SELECT c FROM Contract c WHERE :currentDate BETWEEN c.contractStartDate AND c.contractEndDate")
    List<Contract> findActiveContracts(LocalDate currentDate);
	
	@Query("SELECT c FROM Contract c WHERE c.managerId = :managerId AND c.approach = 'ARTIST'")    
	List<Contract> findContractsByManagerIdAndApproacedByArtist(Long managerId);
}
