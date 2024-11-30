package com.example.SongAddition.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SongAddition.model.Songs;

@Repository
public interface SongRepository extends JpaRepository<Songs, Long> {
}

