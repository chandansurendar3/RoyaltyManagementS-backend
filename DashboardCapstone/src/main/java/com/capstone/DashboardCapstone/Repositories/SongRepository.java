package com.capstone.DashboardCapstone.Repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.DashboardCapstone.Entites.Songs;

public interface SongRepository extends JpaRepository<Songs, Long> {
	@Query("SELECT s FROM Songs s WHERE s.artist.id = :artistId")
    List<Songs> findByArtistIdUsingQuery(@Param("artistId") Long artistId);
}

