package com.example.withdrawal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.withdrawal.modal.BankDetails;
import com.example.withdrawal.repositories.BankDetailsRepo;

@CrossOrigin(value= {"http://localhost:3000","https://royalty-management-system-kdrb.vercel.app/"})
@RestController
@RequestMapping("/bankDetails")
public class BankDetailsController {
	
	@Autowired
	BankDetailsRepo repo;

	@PostMapping("/addBankDetails/{userId}")
	public ResponseEntity<BankDetails> addDetails(@RequestBody BankDetails bd){
		
		 BankDetails acc = repo.save(bd);
		return new ResponseEntity<>(acc,HttpStatus.OK);
		}


    public List<BankDetails> getAllBankDetails() {
        return repo.findAll();
    }
	
//	@GetMapping("/getBankDetails")
//	public ResponseEntity<BankDetails> get(@PathVariable String accNo){
//		//System.out.println("yoo");
//		//BankDetails acc = repo.findByaccountNumber(accNo);
//		
//		return new ResponseEntity<> (acc,HttpStatus.OK);
//		
//	} 
	
	

}
