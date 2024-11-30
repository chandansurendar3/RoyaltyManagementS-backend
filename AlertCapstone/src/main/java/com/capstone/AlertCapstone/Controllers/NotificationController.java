package com.capstone.AlertCapstone.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.AlertCapstone.Modules.NotificationResponse;
import com.capstone.AlertCapstone.Service.NotificationService;

@CrossOrigin(value={"http://localhost:3000/","https://royalty-management-system-kdrb.vercel.app/"})
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/artist/{id}")
    public NotificationResponse getArtistNotifications(@PathVariable Long id) {
        return notificationService.getArtistNotifications(id);
    }

    @PatchMapping("/artist/{id}/update-flags-streams")
    public ResponseEntity<Void> updateArtistFlagsStreams(@PathVariable Long id) {
        notificationService.updateArtistFlagsStreams(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/artist/{id}/update-flags-payment")
    public ResponseEntity<Void> updateArtistFlagsPayment(@PathVariable Long id) {
        notificationService.updateArtistFlagsPayment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/artist/{id}/update-flags-royalty")
    public ResponseEntity<Void> updateArtistFlagsRoyalty(@PathVariable Long id) {
        notificationService.updateArtistFlagsRoyalty(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/artist/{id}/update-flags-streams-payment")
    public ResponseEntity<Void> updateArtistFlagsStreamsPayment(@PathVariable Long id) {
        notificationService.updateArtistFlagsStreamsPayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{id}")
    public NotificationResponse getManagerNotifications(@PathVariable Long id) {
        return notificationService.getManagerNotifications(id);
    }

    @PatchMapping("/manager/{id}/update-flags")
    public ResponseEntity<Void> updateManagerFlags(@PathVariable Long id) {
        notificationService.updateManagerFlags(id);
        return ResponseEntity.noContent().build();
    }
}
