package com.example.rms_microservice.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DailySettlement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long settlementId;

    private Long userId;
    public Long getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Long settlementId) {
		this.settlementId = settlementId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getRoyalty() {
		return royalty;
	}

	public void setRoyalty(double royalty) {
		this.royalty = royalty;
	}

	private LocalDate date;
    private double royalty;

    public DailySettlement() {}

    public DailySettlement(Long userId, LocalDate date, double royalty) {
        this.userId = userId;
        this.date = date;
        this.royalty = royalty;
    }

}
