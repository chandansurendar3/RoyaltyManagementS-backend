package com.example.rms_microservice.repository;
import com.example.rms_microservice.model.DailySettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface DailySettlementRepository extends JpaRepository<DailySettlement, Long> {
    Optional<DailySettlement> findByUserIdAndDate(Long userId, LocalDate date);
    DailySettlement findByDateAndUserId(LocalDate date, Long userId);
}
