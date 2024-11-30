package com.example.withdrawal.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.withdrawal.modal.MonthlySettlement;

public interface MonthlySettlementsRepo extends JpaRepository<MonthlySettlement, Integer> {
    List<MonthlySettlement> findByUserIdAndDateBetween(int userId, LocalDate startDate, LocalDate endDate);
}