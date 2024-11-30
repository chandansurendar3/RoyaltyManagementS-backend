package com.example.rms_microservice.repository;

import com.example.rms_microservice.model.RoyaltyContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<RoyaltyContract, Long> {
    List<RoyaltyContract> findByArtistIdAndStatus(Long artistId, String status);
    Optional<RoyaltyContract> findByArtistId(Long artistId);
   
}
