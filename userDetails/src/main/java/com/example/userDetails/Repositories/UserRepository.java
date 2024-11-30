package com.example.userDetails.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userDetails.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
