package com.capstone.AlertCapstone.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capstone.AlertCapstone.Entities.Payment;

import jakarta.transaction.Transactional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findByUserIdAndFlagTrue(Long artistId);

    @Transactional
    @Modifying
    @Query("UPDATE Payment p SET p.flag = false WHERE p.userId = :artistId")
    void updateFlagByArtistId(@Param("artistId") Long artistId);
}
