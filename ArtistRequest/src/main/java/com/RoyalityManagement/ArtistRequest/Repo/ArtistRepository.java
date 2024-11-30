package com.RoyalityManagement.ArtistRequest.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RoyalityManagement.ArtistRequest.Entity.Artists;

public interface ArtistRepository extends JpaRepository<Artists,Long>{

}
