package com.example.userDetails.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manager_id")
    private Long managerid;
    private String manager_name;
   // private String country;
    private String company;
  //  private String phone;

//    @ManyToOne
//    @JoinColumn(name = "userid", nullable = false)
//    private User user;
    // getters and setters

	public Long getManagerid() {
		return managerid;
	}

	public void setManagerid(Long managerid) {
		this.managerid = managerid;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}

//	public String getPhone() {
//		return phone;
//	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

//	public void setPhone(String phone) {
//		this.phone = phone;
//	}

	@Override
	public String toString() {
		return "Manager [managerid=" + managerid + ", manager_name=" + manager_name 
				+ "]";
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	
}
