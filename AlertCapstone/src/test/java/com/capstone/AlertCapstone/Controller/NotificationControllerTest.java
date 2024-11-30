package com.capstone.AlertCapstone.Controller;

import com.capstone.AlertCapstone.Controllers.NotificationController;
import com.capstone.AlertCapstone.Modules.NotificationResponse;
import com.capstone.AlertCapstone.Service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetArtistNotifications() {
        Long artistId = 1L;
        NotificationResponse response = new NotificationResponse();
        when(notificationService.getArtistNotifications(artistId)).thenReturn(response);

        NotificationResponse result = notificationController.getArtistNotifications(artistId);

        assertEquals(response, result);
    }

    @Test
    public void testUpdateArtistFlagsStreams() {
        Long artistId = 1L;

        ResponseEntity<Void> response = notificationController.updateArtistFlagsStreams(artistId);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(notificationService, times(1)).updateArtistFlagsStreams(artistId);
    }

    @Test
    public void testUpdateArtistFlagsPayment() {
        Long artistId = 1L;

        ResponseEntity<Void> response = notificationController.updateArtistFlagsPayment(artistId);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(notificationService, times(1)).updateArtistFlagsPayment(artistId);
    }

    @Test
    public void testUpdateArtistFlagsRoyalty() {
        Long artistId = 1L;

        ResponseEntity<Void> response = notificationController.updateArtistFlagsRoyalty(artistId);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(notificationService, times(1)).updateArtistFlagsRoyalty(artistId);
    }

    @Test
    public void testUpdateArtistFlagsStreamsPayment() {
        Long artistId = 1L;

        ResponseEntity<Void> response = notificationController.updateArtistFlagsStreamsPayment(artistId);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(notificationService, times(1)).updateArtistFlagsStreamsPayment(artistId);
    }

    @Test
    public void testGetManagerNotifications() {
        Long managerId = 1L;
        NotificationResponse response = new NotificationResponse();
        when(notificationService.getManagerNotifications(managerId)).thenReturn(response);

        NotificationResponse result = notificationController.getManagerNotifications(managerId);

        assertEquals(response, result);
    }

    @Test
    public void testUpdateManagerFlags() {
        Long managerId = 1L;

        ResponseEntity<Void> response = notificationController.updateManagerFlags(managerId);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(notificationService, times(1)).updateManagerFlags(managerId);
    }
}
