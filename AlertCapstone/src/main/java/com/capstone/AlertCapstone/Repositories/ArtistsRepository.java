package com.capstone.AlertCapstone.Repositories;

import com.capstone.AlertCapstone.Entities.Artists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistsRepository extends JpaRepository<Artists, Long> {
}
