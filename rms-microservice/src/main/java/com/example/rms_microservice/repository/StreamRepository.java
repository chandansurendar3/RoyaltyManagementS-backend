package com.example.rms_microservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rms_microservice.model.Streams;

public interface StreamRepository extends JpaRepository<Streams, Long> {
    Optional<Streams> findTopBySongIdOrderByDateDesc(Long songId);
    Optional<Streams> findTopByOrderByDateDesc();
    
    
}

