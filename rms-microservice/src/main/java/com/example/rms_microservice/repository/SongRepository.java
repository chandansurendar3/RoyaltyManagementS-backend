package com.example.rms_microservice.repository;


import com.example.rms_microservice.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Songs, Long> {
}

