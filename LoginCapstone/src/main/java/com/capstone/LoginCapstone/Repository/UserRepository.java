package com.capstone.LoginCapstone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.LoginCapstone.Entity.User;



public interface UserRepository extends JpaRepository<User , Long>{
    Optional<User> findByUsername(String Username);
    Optional<User> findByEmailid(String emailid);
}
