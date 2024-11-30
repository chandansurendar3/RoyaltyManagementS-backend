package com.example.userDetails.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userDetails.model.Manager;
import com.example.userDetails.model.User;

public interface ManagerRepository extends JpaRepository<Manager,Long>{
	// Manager findByUser(User user);
}
