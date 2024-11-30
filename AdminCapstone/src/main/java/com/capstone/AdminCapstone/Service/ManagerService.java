package com.capstone.AdminCapstone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.AdminCapstone.Entities.Managers;
import com.capstone.AdminCapstone.Repository.ManagerRepository;



@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Transactional
	public void saveManger(Managers manager) {
		managerRepository.save(manager);
	}

}
