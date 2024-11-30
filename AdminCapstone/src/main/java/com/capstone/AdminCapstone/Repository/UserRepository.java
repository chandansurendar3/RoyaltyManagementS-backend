package com.capstone.AdminCapstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.AdminCapstone.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	boolean existsByUsername(String username);
	
	boolean existsByEmailid(String emailid);
	
	List<User> findByIsDeletedFalse();
}
