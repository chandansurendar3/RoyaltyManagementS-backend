package com.example.withdrawal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.withdrawal.modal.Transaction1;
import com.example.withdrawal.repositories.TransactionRepository;
import com.example.withdrawal.service.TransactionService;

@CrossOrigin(value= {"http://localhost:3000","https://royalty-management-system-kdrb.vercel.app/"})
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired 
    private TransactionRepository transactionRepository;
    @PostMapping("/trigger-payments")
    public void triggerPayments() {
        transactionService.automatePayments();
    }
    
    
        @GetMapping("/get/{userId}")
        public List<Transaction1> getTransactionsByArtistId(@PathVariable int userId) {
            return transactionService.getTransactionsByUserId(userId);
        }
        

        @Scheduled(cron = "0 0 0 * * ?") // 1 day interval
            @GetMapping("/automate-payments")
            public String automatePayments() {
                transactionService.automatePayments();
                return "Payments automated successfully.";
            }
    
//    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
//        // Save the transaction
//        Transaction savedTransaction = transactionService.save(transaction);
//        return new ResponseEntity<>(savedTransaction,HttpStatus.OK);
//    }
}

//public ResponseEntity<AccountInfo> addEmployee(@RequestBody AccountInfo ac){
//	
//	 AccountInfo acc = acrepo.save(ac);
//	return new ResponseEntity<>(acc,HttpStatus.OK);
//	}