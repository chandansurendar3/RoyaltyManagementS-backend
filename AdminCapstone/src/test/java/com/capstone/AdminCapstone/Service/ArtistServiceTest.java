package com.capstone.AdminCapstone.Service;


import com.capstone.AdminCapstone.Entities.Role;
import com.capstone.AdminCapstone.Entities.Artists;
import com.capstone.AdminCapstone.Repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    @Test
    void testSaveArtist() {
        // Given
        Artists artist = new Artists();
        artist.setArtistid(1L);
        artist.setPhoneNo("1234567890");
        artist.setStageName("Test Stage Name");
        artist.setRealName("Test Real Name");
        artist.setCountry("Test Country");

        // When
        artistService.saveArtist(artist);

        // Then
        verify(artistRepository, times(1)).save(artist);
    }
}

