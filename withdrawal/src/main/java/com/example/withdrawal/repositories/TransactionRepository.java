package com.example.withdrawal.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.withdrawal.modal.Transaction1;

//public interface TransactionRepository extends JpaRepository<Transaction1, String> {
//    public List<Transaction1> findByUserId(int id);	
//  //  List<Transaction1> findByUserIdAndDateCreatedBetween(int userId, LocalDate startDate, LocalDate endDate);
//
//}

public interface TransactionRepository extends JpaRepository<Transaction1, Integer> {
	public List<Transaction1> findByUserId(int id);
    @Query("SELECT t FROM Transaction1 t WHERE t.userId = :userId AND FUNCTION('MONTH', t.dateCreated) = :month AND FUNCTION('YEAR', t.dateCreated) = :year")
   public List<Transaction1> findByUserIdAndMonth(@Param("userId") int userId, @Param("month") int month, @Param("year") int year);
    
  public  Transaction1 findTopByUserIdOrderByDateCreatedDesc(int userId);

}