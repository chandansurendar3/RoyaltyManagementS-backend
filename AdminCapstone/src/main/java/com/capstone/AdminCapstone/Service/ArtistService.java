package com.capstone.AdminCapstone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.AdminCapstone.Entities.Artists;
import com.capstone.AdminCapstone.Repository.ArtistRepository;


@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

  
    
    @Transactional
    public void saveArtist(Artists artist) {
        artistRepository.save(artist);
    }
}
