package com.example.withdrawal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.withdrawal.modal.BankDetails;
import com.example.withdrawal.modal.MonthlySettlement;
import com.example.withdrawal.modal.Transaction1;
import com.example.withdrawal.repositories.BankDetailsRepo;
import com.example.withdrawal.repositories.MonthlySettlementsRepo;
import com.example.withdrawal.repositories.TransactionRepository;

//@Service
//public class TransactionService {
//    
//    @Autowired
//    private TransactionRepository transactionRepository;
//    
//    @Autowired
//    private BankDetailsRepo bankDetailsRepository;
//    
//    @Autowired
//    private EarningsRepo erep;
//    @Autowired
//    private MonthlySettlementsRepo monthlySettlementsRepo; 
//    
//    public List<Transaction1> getTransactionsByUserId(int userId) {
//        return transactionRepository.findByUserId(userId);
//    }
//
////    @Scheduled(cron = "0 0 0 1 * *") // Executes at midnight on the 1st day of every month
//    @Scheduled(fixedRate = 3000000)
//    public void automatePayments() {
//        // Get bank details for all artists
//        List<BankDetails> bankDetailsList = bankDetailsRepository.findAll();
//        
//        LocalDate now = LocalDate.now();
//        YearMonth currentMonth = YearMonth.from(now);
//        LocalDate startOfMonth = currentMonth.atDay(1);
//        LocalDate endOfMonth = currentMonth.atEndOfMonth();
//        
//        
//        List<Earnings> erlist = erep.findAll();
//
//        // Iterate through bank details and make payments
//        for (BankDetails bankDetails : bankDetailsList) {
//            // Implement payment logic here, e.g., create a transaction record
//            Transaction1 transaction = new Transaction1();
//       //     transaction.setAmount(1000); // assume salary amount is 1000
//            Earnings earnings = erep.findByUserId(bankDetails.getUserId());
//            
////            MonthlySettlement monthlyPayments = monthlySettlementsRepo.findByUserId(bankDetails.getUserId());
//            List<MonthlySettlement> monthlyTransactionsList = monthlySettlementsRepo.findByUserIdAndTransactionDateBetween(
//                    bankDetails.getUserId(), startOfMonth, endOfMonth);
//            
//            double totalMonthlyPayments = monthlyTransactionsList.stream()
//                    .mapToDouble(MonthlySettlement::getDailyRoyalty)
//                    .sum();
//
//            transaction.setDateCreated(LocalDate.now());
//            transaction.setAccountNumber(bankDetails.getAccountNumber());
//            transaction.setUsername(bankDetails.getUsername());
//            transaction.setBankName(bankDetails.getBankName());
//            //should receive amount from Chirag 
//            transaction.setAmount(totalMonthlyPayments);
//            transaction.setUserId(bankDetails.getUserId());
//         //   transaction.setEarning(earnings.getEarning());
//
//
//
//            // Save the transaction
//            transactionRepository.save(transaction);
//        }
//    }
//}
//
////List<MonthlySettlement> monthlyPayments = monthlySettlementsRepo.findByUserId(bankDetails.getUserId());





//@Service
//public class TransactionService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private BankDetailsRepo bankDetailsRepository;
//
//    @Autowired
//    private EarningsRepo erep;
//
//    @Autowired
//    private MonthlySettlementsRepo monthlySettlementsRepo;
//
//    @Autowired
//    private TransactionRepository monthlyTransactionsRepo;
//
//    private LocalDate nextDate = LocalDate.of(2024, 3, 17); // Initialize to the desired start date
//    private LocalDate nextMonth = LocalDate.of(2024, 3, 17);
//    public List<Transaction1> getTransactionsByUserId(int userId) {
//        return transactionRepository.findByUserId(userId);
//    }
//
//    @Scheduled(fixedRate = 300000) // Run every 5 minutes
//    public void automatePayments() {
//        // Get bank details for all artists
//        List<BankDetails> bankDetailsList = bankDetailsRepository.findAll();
//
//        // Get current date and the first and last day of the current month
//        LocalDate now = LocalDate.now();
//        YearMonth currentMonth = YearMonth.from(now);
//        LocalDate startOfMonth = currentMonth.atDay(1);
//        LocalDate endOfMonth = currentMonth.atEndOfMonth();
//
//        // Iterate through bank details and make payments
//        for (BankDetails bankDetails : bankDetailsList) {
//            // Create a transaction record
//            Transaction1 transaction = new Transaction1();
//
//            // Fetch earnings and monthly payments for the user
//            Earnings earnings = erep.findByUserId(bankDetails.getUserId());
//            List<MonthlySettlement> monthlyTransactionsList = monthlySettlementsRepo.findByUserIdAndTransactionDateBetween(
//                    bankDetails.getUserId(), startOfMonth, endOfMonth);
//
//            // Calculate total monthly payments for the current month
//            double totalMonthlyPayments = monthlyTransactionsList.stream()
//                    .mapToDouble(MonthlySettlement::getDailyRoyalty)
//                    .sum();
//
//            // Set transaction details
//           // transaction.setDateCreated(nextDate.atStartOfDay()); // Use the tracked date
//            transaction.setDateCreated(nextDate.atStartOfDay());
//            transaction.setAccountNumber(bankDetails.getAccountNumber());
//            transaction.setUsername(bankDetails.getUsername());
//            transaction.setBankName(bankDetails.getBankName());
//            transaction.setAmount(totalMonthlyPayments);
//            transaction.setUserId(bankDetails.getUserId());
//
//            // Save the transaction
//            transactionRepository.save(transaction);
//        }
//
//        // Increment the date by one day for the next run
//        nextDate = nextDate.plusDays(1);
//        nextMonth = nextMonth.plusMonths(1);
//    }
//}

//last one

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.YearMonth;
//import java.util.List;
//
//@Service
//public class TransactionService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private BankDetailsRepo bankDetailsRepository;
//
//    @Autowired
//    private EarningsRepo erep;
//
//    @Autowired
//    private MonthlySettlementsRepo monthlySettlementsRepo;
//
//    private YearMonth nextMonth = YearMonth.of(2024, 3); // Initialize to the desired start month
//
//    public List<Transaction1> getTransactionsByUserId(int userId) {
//        return transactionRepository.findByUserId(userId);
//    }
//
////    @Scheduled(cron = "0 0 0 1 * ?") // Run at midnight on the first day of every month
//    @Scheduled(fixedRate = 300000)
//    public void automatePayments() {
//        // Get bank details for all artists
//        List<BankDetails> bankDetailsList = bankDetailsRepository.findAll();
//
//        // Calculate the start and end of the next month
//        LocalDate startOfNextMonth = nextMonth.atDay(1);
//        LocalDate endOfNextMonth = nextMonth.atEndOfMonth();
//
//        // Iterate through bank details and make payments
//        for (BankDetails bankDetails : bankDetailsList) {
//            // Create a transaction record
//            Transaction1 transaction = new Transaction1();
//
//            // Fetch earnings and monthly payments for the user
//            Earnings earnings = erep.findByUserId(bankDetails.getUserId());
//            List<MonthlySettlement> monthlyTransactionsList = monthlySettlementsRepo.findByUserIdAndTransactionDateBetween(
//                    bankDetails.getUserId(), startOfNextMonth, endOfNextMonth);
//
//            // Calculate total monthly payments for the specified month
//            double totalMonthlyPayments = monthlyTransactionsList.stream()
//                    .mapToDouble(MonthlySettlement::getDailyRoyalty)
//                    .sum();
//
//            // Set transaction details
//            transaction.setDateCreated(LocalDateTime.now()); // Set the date created to current date and time
//            transaction.setAccountNumber(bankDetails.getAccountNumber());
//            transaction.setUsername(bankDetails.getUsername());
//            transaction.setBankName(bankDetails.getBankName());
//            transaction.setAmount(totalMonthlyPayments);
//            transaction.setUserId(bankDetails.getUserId());
//
//            // Save the transaction
//            transactionRepository.save(transaction);
//        }
//
//        // Increment the month for the next run
//        nextMonth = nextMonth.plusMonths(1);
//    }
//}


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Service
//public class TransactionService {
//
//    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private BankDetailsRepo bankDetailsRepository;
//
//    @Autowired
//    private EarningsRepo erep;
//
//    @Autowired
//    private MonthlySettlementsRepo monthlySettlementsRepo;
//
//    private YearMonth nextMonth = YearMonth.of(2024, 3); // Initialize to the desired start month
//
//    public List<Transaction1> getTransactionsByUserId(int userId) {
//        return transactionRepository.findByUserId(userId);
//    }
//
//    @Scheduled(fixedRate = 300000) // Run every 5 minutes
//    public void automatePayments() {
//        // Get bank details for all artists
//        List<BankDetails> bankDetailsList = bankDetailsRepository.findAll();
//
//        // Calculate the start and end of the next month
//        LocalDate startOfNextMonth = nextMonth.atDay(1);
//        LocalDate endOfNextMonth = nextMonth.atEndOfMonth();
//
//        // Iterate through bank details and make payments
//        for (BankDetails bankDetails : bankDetailsList) {
//            // Create a transaction record
//            Transaction1 transaction = new Transaction1();
//
//            // Fetch earnings and monthly payments for the user
//            Earnings earnings = erep.findByUserId(bankDetails.getUserId());
//            List<MonthlySettlement> monthlyTransactionsList = monthlySettlementsRepo.findByUserIdAndTransactionDateBetween(
//                    bankDetails.getUserId(), startOfNextMonth, endOfNextMonth);
//            System.out.println(monthlyTransactionsList);
//
//            // Log the contents of monthlyTransactionsList
//            logger.info("Monthly transactions for userId {}: {}", bankDetails.getUserId(), monthlyTransactionsList);
//
//            // Calculate total monthly payments for the specified month
//            double totalMonthlyPayments = monthlyTransactionsList.stream()
//                    .mapToDouble(MonthlySettlement::getDailyRoyalty)
//                    .sum();
//
//            // Log the calculated total monthly payments
//            logger.info("Total monthly payments for userId {}: {}", bankDetails.getUserId(), totalMonthlyPayments);
//
//            // Set transaction details
//            transaction.setDateCreated(LocalDateTime.now()); // Set the date created to current date and time
//            transaction.setAccountNumber(bankDetails.getAccountNumber());
//            transaction.setUsername(bankDetails.getUsername());
//            transaction.setBankName(bankDetails.getBankName());
//            transaction.setAmount(totalMonthlyPayments);
//            transaction.setUserId(bankDetails.getUserId());
//
//            // Save the transaction
//            transactionRepository.save(transaction);
//        }
//
//        // Increment the month for the next run
//        nextMonth = nextMonth.plusMonths(1);
//    }
//}
//


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankDetailsRepo bankDetailsRepository;

    @Autowired
    private MonthlySettlementsRepo earningsRepository;

    public List<Transaction1> getTransactionsByUserId(int userId) {
        return transactionRepository.findByUserId(userId);
    }


    public void automatePayments() {
        // Get bank details for all users
        List<BankDetails> bankDetailsList = bankDetailsRepository.findAll();

        // Iterate through bank details and make payments
        for (BankDetails bankDetails : bankDetailsList) {
            int userId = bankDetails.getUserId();
            
            // Find the last transaction for the user
            Transaction1 lastTransaction = transactionRepository.findTopByUserIdOrderByDateCreatedDesc(userId);
            
            // Determine the starting month
            YearMonth startMonth = (lastTransaction == null) ? YearMonth.of(2024, 3) : YearMonth.from(lastTransaction.getDateCreated()).plusMonths(1);
            YearMonth currentMonth = YearMonth.now();

            YearMonth month = startMonth;
       //    while (!month.isAfter(currentMonth)) {
            while (!month.isAfter(YearMonth.of(2026,1))) {
                // Calculate total earnings for the month
                double totalEarnings = getTotalEarningsForMonth(userId, month);
                if (totalEarnings > 0) {
                    // Create a transaction record for the month
                    Transaction1 transaction = new Transaction1();
                    transaction.setAccountNumber(bankDetails.getAccountNumber());
                    transaction.setUsername(bankDetails.getUsername());
                    transaction.setBankName(bankDetails.getBankName());
                    transaction.setUserId(userId);
                    transaction.setAmount(totalEarnings);
                    System.out.println(month);
                    transaction.setDateCreated(month.atEndOfMonth());

                    // Save the transaction
                    transactionRepository.save(transaction);
                    System.out.println("Saved transaction for user " + userId + " for month " + month + ": " + totalEarnings);
                }

                // Move to the next month
                month = month.plusMonths(1);
            }
        }
    }

    private double getTotalEarningsForMonth(int userId, YearMonth month) {
        // Get the start and end dates for the month
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();

        // Fetch all earnings for the user in the specified month
        List<MonthlySettlement> earningsList = earningsRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
        System.out.println("Earnings list for user " + userId + " from " + startDate + " to " + endDate + ": " + earningsList);

        // Sum up the earnings
        double totalEarnings = earningsList.stream().mapToDouble(MonthlySettlement::getDailyRoyalty).sum();
        System.out.println("Total earnings for user " + userId + " for " + month + ": " + totalEarnings);

        return totalEarnings;
    }
}

