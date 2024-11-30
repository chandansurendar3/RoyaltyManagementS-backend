package com.example.withdrawal.modal;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DailySettlement")
public class MonthlySettlement {
	@Id
	@Column(name = "settlementId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int userId ;
	@Column(name = "royalty")
	private double dailyRoyalty;
	private LocalDate date;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public double getDailyRoyalty() {
		return dailyRoyalty;
	}
	public void setDailyRoyalty(double dailyRoyalty) {
		this.dailyRoyalty = dailyRoyalty;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	

}
