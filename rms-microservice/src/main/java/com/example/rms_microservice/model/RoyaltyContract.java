package com.example.rms_microservice.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RoyaltyContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    private Long artistId;
    @Column(name = "artistPercentageOfRoyalty")
    private Double artistPct;
    private Long managerId;
    @Column(name = "managerPercentageOfRoyalty")
    private Double managerPct;
    @Column(name = "contractStartDate")
    private LocalDate fromDate;
    @Column(name = "contractEndDate")
    private LocalDate toDate;
    private String status;
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Long getArtistId() {
		return artistId;
	}
	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}
	public Double getArtistPct() {
		return artistPct;
	}
	public void setArtistPct(Double artistPct) {
		this.artistPct = artistPct;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	public Double getManagerPct() {
		return managerPct;
	}
	public void setManagerPct(Double managerPct) {
		this.managerPct = managerPct;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
