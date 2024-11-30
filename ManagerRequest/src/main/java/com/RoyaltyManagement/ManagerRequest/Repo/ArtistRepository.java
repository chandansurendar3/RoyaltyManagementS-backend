package com.RoyaltyManagement.ManagerRequest.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RoyaltyManagement.ManagerRequest.Entity.Artists;


public interface ArtistRepository extends JpaRepository<Artists,Long>{

	  List<Artists> findByManagerIdIsNull();
}
