package com.capstone.AlertCapstone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.AlertCapstone.Entities.Streams;

import jakarta.transaction.Transactional;

public interface StreamsRepository extends JpaRepository<Streams, Long> {
    Streams findTopByOrderByStreamsDesc();
    @Transactional
    @Modifying
    @Query("UPDATE Streams s SET s.flag = false WHERE s.song.artist.artistId = :artistId")
    void updateFlagByArtistId(@Param("artistId") Long artistId);

}
