package com.capstone.AdminCapstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.AdminCapstone.Entities.Artists;

public interface ArtistRepository extends JpaRepository<Artists, Long>{

	Artists findByArtistid(Long userId);
	List<Artists> findByManagerid(Long managerid);
}
