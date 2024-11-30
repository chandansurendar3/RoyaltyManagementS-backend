//package com.RoyalityManagement.ArtistRequest.Controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import com.RoyalityManagement.ArtistRequest.Entity.Artists;
//import com.RoyalityManagement.ArtistRequest.Repo.ArtistRepository;
//
//public class ArtistControllerTest {
//
//    @Mock
//    private ArtistRepository artistRepository;
//
//    @InjectMocks
//    private ArtistController artistController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testUpdateArtist() {
//        Artists artist = new Artists();
//        artist.setArtistid(1L);
//        artist.setManager_id(2L);
//
//        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
//        when(artistRepository.save(artist)).thenReturn(artist);
//
//        Map<String, Long> updates = new HashMap<>();
//        updates.put("managerId", 3L);
//
//        ResponseEntity<Artists> response = artistController.updateArtist(1L, updates);
//
//        assertEquals(3L, artist.getManager_id());
//        assertEquals(200, response.getStatusCodeValue());
//    }
//
//    @Test
//    public void testUpdateArtistNotFound() {
//        when(artistRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Map<String, Long> updates = new HashMap<>();
//        updates.put("managerId", 3L);
//
//        ResponseEntity<Artists> response = artistController.updateArtist(1L, updates);
//
//        assertEquals(404, response.getStatusCodeValue());
//    }
//}
