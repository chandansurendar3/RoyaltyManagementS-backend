package com.example.withdrawal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.withdrawal.modal.BankDetails;

public interface BankDetailsRepo extends JpaRepository<BankDetails, String> {
	
	//public BankDetails findByaccountNumber(String accountNo);
	
}
