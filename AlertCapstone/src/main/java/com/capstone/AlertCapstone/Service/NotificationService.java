package com.capstone.AlertCapstone.Service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.AlertCapstone.Entities.Artists;
import com.capstone.AlertCapstone.Entities.Managers;
import com.capstone.AlertCapstone.Entities.Payment;
import com.capstone.AlertCapstone.Entities.RoyaltyContract;
import com.capstone.AlertCapstone.Entities.Streams;
import com.capstone.AlertCapstone.Entities.Enums.Approach;
import com.capstone.AlertCapstone.Modules.NotificationResponse;
import com.capstone.AlertCapstone.Repositories.ArtistsRepository;
import com.capstone.AlertCapstone.Repositories.ManagersRepository;
import com.capstone.AlertCapstone.Repositories.PaymentRepository;
import com.capstone.AlertCapstone.Repositories.RoyaltyContractRepository;
import com.capstone.AlertCapstone.Repositories.StreamsRepository;

@Service
public class NotificationService {

    @Autowired
    private ArtistsRepository artistsRepository;

    @Autowired
    private ManagersRepository managersRepository;

    @Autowired
    private StreamsRepository streamsRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RoyaltyContractRepository royaltyContractRepository;

    public NotificationResponse getArtistNotifications(Long id) {
        NotificationResponse response = new NotificationResponse();

        // Check Streams table
        Streams topStream = streamsRepository.findTopByOrderByStreamsDesc();
        if (topStream != null && topStream.getFlag() && topStream.getSong().getArtist().getArtistId().equals(id)) {
            response.setStreams("Congrats your song '" + topStream.getSong().getTitle() + "' is the most viewed.");
        }

        List<Payment> payments = paymentRepository.findByUserIdAndFlagTrue(id);
        if (payments != null && !payments.isEmpty()) {
            List<String> payoutMessages = new ArrayList<>();
            for (Payment payment : payments) {
                payoutMessages.add("Your Royalty payments credited: " + payment.getPaymentAmount());
            }
            response.setPayout(payoutMessages);
        }

        // Check RoyaltyContract table
        RoyaltyContract royaltyContract = royaltyContractRepository.findTopByArtist_ArtistIdAndStatusAndApproached(id,"PENDING",Approach.MANAGER);
        if (royaltyContract != null) {
            Managers manager = royaltyContract.getManager();
            if (manager != null) {
                response.setRequest(manager.getCompany() + " has offered a partnership.");
            }
        }

        return response;
    }

    public void updateArtistFlagsStreams(Long id) {
        streamsRepository.updateFlagByArtistId(id);
    }

    public void updateArtistFlagsPayment(Long id) {
        paymentRepository.updateFlagByArtistId(id);
    }

    public void updateArtistFlagsRoyalty(Long id) {
        royaltyContractRepository.updateFlagByArtistIdOrManagerId(id, null);
    }

    public void updateArtistFlagsStreamsPayment(Long id) {
        streamsRepository.updateFlagByArtistId(id);
        paymentRepository.updateFlagByArtistId(id);
    }

    public NotificationResponse getManagerNotifications(Long id) {
        NotificationResponse response = new NotificationResponse();

        // Check RoyaltyContract table
        RoyaltyContract royaltyContract = royaltyContractRepository.findTopByManager_ManagerIdAndStatusAndApproached(id,"PENDING",Approach.ARTIST);
        if (royaltyContract != null) {
            Artists artist = royaltyContract.getArtist();
            if (artist != null) {
                response.setRequest("Artist '" + artist.getStageName() + "' has offered a partnership.");
            }
        }

        return response;
    }

    public void updateManagerFlags(Long id) {
        royaltyContractRepository.updateFlagByArtistIdOrManagerId(null, id);
    }
}
