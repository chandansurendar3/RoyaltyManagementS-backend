package com.RoyaltyManagement.ManagerRequest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RoyaltyManagement.ManagerRequest.Entity.Artists;
import com.RoyaltyManagement.ManagerRequest.Repo.ArtistRepository;


@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    
    //gets list of artists with null managers
    public List<Artists> getArtistsWithNullManagerId() {
        return artistRepository.findByManagerIdIsNull();
    }
    

}
