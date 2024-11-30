package com.capstone.AdminCapstone.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.AdminCapstone.Entities.Managers;



public interface ManagerRepository extends JpaRepository<Managers, Long>{
	
	Managers findByManagerid(Long userId);
}
