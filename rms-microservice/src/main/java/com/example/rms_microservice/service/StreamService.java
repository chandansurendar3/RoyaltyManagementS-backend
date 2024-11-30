package com.example.rms_microservice.service;


import com.example.rms_microservice.model.Streams;
import com.example.rms_microservice.repository.StreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class StreamService {
    private static final double TIER_1_RATE = 0.002;
    private static final double TIER_2_RATE = 0.005;
    private static final double TIER_3_RATE = 0.01;
    private static final long TIER_1_THRESHOLD = 10000;
    private static final long TIER_2_THRESHOLD = 50000;

    @Autowired
    private StreamRepository streamRepository;

    public double calculateRoyalty(Long streams) {
        double royalty = 0.0;
        if (streams <= TIER_1_THRESHOLD) {
            royalty = streams * TIER_1_RATE;
        } else if (streams <= TIER_2_THRESHOLD) {
            royalty = TIER_1_THRESHOLD * TIER_1_RATE + (streams - TIER_1_THRESHOLD) * TIER_2_RATE;
        } else {
            royalty = TIER_1_THRESHOLD * TIER_1_RATE + (TIER_2_THRESHOLD - TIER_1_THRESHOLD) * TIER_2_RATE
                    + (streams - TIER_2_THRESHOLD) * TIER_3_RATE;
        }
        return royalty;
    }

    public Optional<Streams> findLatestStreamBySongId(Long songId) {
        return streamRepository.findTopBySongIdOrderByDateDesc(songId);
    }

    public void saveStream(Streams stream) {
        streamRepository.save(stream);
    }
    public LocalDate findLatestStreamDate() {
        return streamRepository.findTopByOrderByDateDesc()
                .map(Streams::getDate)
                .orElse(LocalDate.of(2024, 3, 19));
    }
}

