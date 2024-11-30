package com.capstone.AlertCapstone.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Managers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;

    public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	public List<Artists> getArtists() {
		return artists;
	}

	@Override
	public String toString() {
		return "Managers [managerId=" + managerId + ", company=" + company +  ", artists="
				+ artists + "]";
	}

	public void setArtists(List<Artists> artists) {
		this.artists = artists;
	}

	private String company;

    @OneToMany(mappedBy = "manager")
    private List<Artists> artists;
}