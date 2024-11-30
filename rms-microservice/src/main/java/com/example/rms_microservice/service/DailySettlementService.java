package com.example.rms_microservice.service;

import com.example.rms_microservice.model.RoyaltyContract;
import com.example.rms_microservice.model.DailySettlement;
import com.example.rms_microservice.model.Streams;
import com.example.rms_microservice.model.Songs;
import com.example.rms_microservice.repository.ContractRepository;
import com.example.rms_microservice.repository.DailySettlementRepository;
import com.example.rms_microservice.repository.StreamRepository;
import com.example.rms_microservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DailySettlementService {

    @Autowired
    private StreamRepository streamRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private DailySettlementRepository dailySettlementRepository;

    public void calculateAndSaveDailySettlements() {
        List<Streams> streams = streamRepository.findAll();
        Map<LocalDate, Map<Long, Double>> artistRoyalties = new HashMap<>();

        for (Streams stream : streams) {
            Songs song = songRepository.findById(stream.getSongId()).orElse(null);
            if (song == null) continue;

            double streamRoyalty = stream.getRoyalty();
            LocalDate streamDate = stream.getDate();

            // Calculate royalties considering collaboration
            double artistRoyalty1 = streamRoyalty;
            double artistRoyalty2 = 0.0;
            if (song.isCollabed()) {
                artistRoyalty1 = streamRoyalty * song.getCollaborationPct() / 100;
                artistRoyalty2 = streamRoyalty * song.getArtist2Pct() / 100;
            }

            // Aggregate royalties for artist 1
            artistRoyalties.putIfAbsent(streamDate, new HashMap<>());
            artistRoyalties.get(streamDate).merge(song.getArtistId(), artistRoyalty1, Double::sum);

            // Aggregate royalties for artist 2 if collaboration exists
            if (song.isCollabed()) {
                artistRoyalties.get(streamDate).merge(song.getCollaborationArtistId(), artistRoyalty2, Double::sum);
            }
        }

        // Distribute royalties based on contracts
        Map<LocalDate, Map<Long, Double>> finalRoyalties = new HashMap<>();
        for (Map.Entry<LocalDate, Map<Long, Double>> entry : artistRoyalties.entrySet()) {
            LocalDate date = entry.getKey();
            Map<Long, Double> dailyRoyalties = entry.getValue();

            for (Map.Entry<Long, Double> royaltyEntry : dailyRoyalties.entrySet()) {
                Long artistId = royaltyEntry.getKey();
                Double totalRoyalty = royaltyEntry.getValue();

                // Apply contract
                RoyaltyContract contract = contractRepository.findByArtistId(artistId).orElse(null);
                if (contract != null && contract.getStatus().equalsIgnoreCase("active")) {
                    double artistShare = totalRoyalty * contract.getArtistPct() / 100;
                    double managerShare = totalRoyalty * contract.getManagerPct() / 100;

                    finalRoyalties.putIfAbsent(date, new HashMap<>());
                    finalRoyalties.get(date).merge(artistId, artistShare, Double::sum);
                    finalRoyalties.get(date).merge(contract.getManagerId(), managerShare, Double::sum);
                } else {
                    // If no contract, 100% goes to the artist
                    finalRoyalties.putIfAbsent(date, new HashMap<>());
                    finalRoyalties.get(date).merge(artistId, totalRoyalty, Double::sum);
                }
            }
        }

        // Save daily settlements
        for (Map.Entry<LocalDate, Map<Long, Double>> entry : finalRoyalties.entrySet()) {
            LocalDate date = entry.getKey();
            Map<Long, Double> royalties = entry.getValue();
            for (Map.Entry<Long, Double> royaltyEntry : royalties.entrySet()) {
                Long userId = royaltyEntry.getKey();
                Double royalty = royaltyEntry.getValue();

                // Check if a settlement for this date and user already exists
                DailySettlement existingSettlement = dailySettlementRepository.findByDateAndUserId(date, userId);
                if (existingSettlement == null) {
                    DailySettlement settlement = new DailySettlement();
                    settlement.setDate(date);
                    settlement.setRoyalty(royalty);
                    settlement.setUserId(userId);

                    dailySettlementRepository.save(settlement);
                } else {
                    // Update the existing settlement with the new calculated royalty
                    existingSettlement.setRoyalty(royalty);
                    dailySettlementRepository.save(existingSettlement);
                }
            }
        }
    }
}


