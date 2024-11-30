package com.capstone.AlertCapstone.Service;


import com.capstone.AlertCapstone.Entities.*;
import com.capstone.AlertCapstone.Entities.Enums.Approach;
import com.capstone.AlertCapstone.Modules.NotificationResponse;
import com.capstone.AlertCapstone.Repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Mock
    private ArtistsRepository artistsRepository;

    @Mock
    private ManagersRepository managersRepository;

    @Mock
    private StreamsRepository streamsRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private RoyaltyContractRepository royaltyContractRepository;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetArtistNotifications() {
        Long artistId = 1L;
        Streams topStream = new Streams();
        topStream.setFlag(true);
        Songs song = new Songs();
        song.setArtist(new Artists());
        song.getArtist().setArtistId(artistId);
        topStream.setSong(song);
        when(streamsRepository.findTopByOrderByStreamsDesc()).thenReturn(topStream);

        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setPaymentAmount(100.0);
        payments.add(payment);
        when(paymentRepository.findByArtist_ArtistIdAndFlagTrue(artistId)).thenReturn(payments);

        RoyaltyContract royaltyContract = new RoyaltyContract();
        royaltyContract.setApproached(Approach.ARTISTS);
        royaltyContract.setFlag(true);
        Managers manager = new Managers();
        manager.setCompany("Company");
        royaltyContract.setManager(manager);
        when(royaltyContractRepository.findTopByArtist_ArtistIdAndFlagTrueAndApproached(artistId, Approach.ARTISTS)).thenReturn(royaltyContract);

        NotificationResponse response = notificationService.getArtistNotifications(artistId);

        assertEquals("Congrats your song 'null' is the most viewed.", response.getStreams());
        assertEquals(1, response.getPayout().size());
        assertEquals("Your Royalty payments credited: 100.0", response.getPayout().get(0));
        assertEquals("Company has offered a partnership.", response.getRequest());
    }

    @Test
    public void testUpdateArtistFlagsStreams() {
        Long artistId = 1L;

        notificationService.updateArtistFlagsStreams(artistId);

        verify(streamsRepository, times(1)).updateFlagByArtistId(artistId);
    }

    @Test
    public void testUpdateArtistFlagsPayment() {
        Long artistId = 1L;

        notificationService.updateArtistFlagsPayment(artistId);

        verify(paymentRepository, times(1)).updateFlagByArtistId(artistId);
    }

    @Test
    public void testUpdateArtistFlagsRoyalty() {
        Long artistId = 1L;

        notificationService.updateArtistFlagsRoyalty(artistId);

        verify(royaltyContractRepository, times(1)).updateFlagByArtistIdOrManagerId(artistId, null);
    }

    @Test
    public void testUpdateArtistFlagsStreamsPayment() {
        Long artistId = 1L;

        notificationService.updateArtistFlagsStreamsPayment(artistId);

        verify(streamsRepository, times(1)).updateFlagByArtistId(artistId);
        verify(paymentRepository, times(1)).updateFlagByArtistId(artistId);
    }

    @Test
    public void testGetManagerNotifications() {
        Long managerId = 1L;
        RoyaltyContract royaltyContract = new RoyaltyContract();
        royaltyContract.setApproached(Approach.MANAGERS);
        royaltyContract.setFlag(true);
        Artists artist = new Artists();
        artist.setStageName("Artist");
        royaltyContract.setArtist(artist);
        when(royaltyContractRepository.findTopByManager_ManagerIdAndFlagTrueAndApproached(managerId, Approach.MANAGERS)).thenReturn(royaltyContract);

        NotificationResponse response = notificationService.getManagerNotifications(managerId);

        assertEquals("Artist 'Artist' has offered a partnership.", response.getRequest());
    }

    @Test
    public void testUpdateManagerFlags() {
        Long managerId = 1L;

        notificationService.updateManagerFlags(managerId);

        verify(royaltyContractRepository, times(1)).updateFlagByArtistIdOrManagerId(null, managerId);
    }
}

