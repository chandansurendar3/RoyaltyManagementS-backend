package com.capstone.AlertCapstone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.AlertCapstone.Entities.RoyaltyContract;
import com.capstone.AlertCapstone.Entities.Enums.Approach;

import jakarta.transaction.Transactional;

public interface RoyaltyContractRepository extends JpaRepository<RoyaltyContract, Long> {
	RoyaltyContract findTopByArtist_ArtistIdAndStatusAndApproached(Long artistId, String status, Approach approached);
    RoyaltyContract findTopByManager_ManagerIdAndStatusAndApproached(Long managerId, String status, Approach approached);
    @Transactional
    @Modifying
    @Query("UPDATE RoyaltyContract rc SET rc.flag = false WHERE rc.artist.artistId = :artistId OR rc.manager.managerId = :managerId")
    void updateFlagByArtistIdOrManagerId(@Param("artistId") Long artistId, @Param("managerId") Long managerId);

}
