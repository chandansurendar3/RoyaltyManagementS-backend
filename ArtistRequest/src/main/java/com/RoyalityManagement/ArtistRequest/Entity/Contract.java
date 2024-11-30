package com.RoyalityManagement.ArtistRequest.Entity;




import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "RoyaltyContract")
public class Contract {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
 
    private Long artistId;
    private Long managerId;
    private int managerPercentageOfRoyalty;
    private int artistPercentageOfRoyalty;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String Status;
    private String approach;
    private String contractStatus;
    
 
    


	







	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", artistId=" + artistId + ", managerId=" + managerId
				+ ", managerPercentageOfRoyalty=" + managerPercentageOfRoyalty + ", artistPercentageOfRoyalty="
				+ artistPercentageOfRoyalty + ", contractStartDate=" + contractStartDate + ", contractEndDate="
				+ contractEndDate + ", Status=" + Status + ", approach=" + approach + ", contractStatus="
				+ contractStatus + "]";
	}













	public Long getContractId() {
		return contractId;
	}













	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}













	public Long getArtistId() {
		return artistId;
	}













	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}













	public Long getManagerId() {
		return managerId;
	}













	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}













	public int getManagerPercentageOfRoyalty() {
		return managerPercentageOfRoyalty;
	}













	public void setManagerPercentageOfRoyalty(int managerPercentageOfRoyalty) {
		this.managerPercentageOfRoyalty = managerPercentageOfRoyalty;
	}













	public int getArtistPercentageOfRoyalty() {
		return artistPercentageOfRoyalty;
	}













	public void setArtistPercentageOfRoyalty(int artistPercentageOfRoyalty) {
		this.artistPercentageOfRoyalty = artistPercentageOfRoyalty;
	}













	public LocalDate getContractStartDate() {
		return contractStartDate;
	}













	public void setContractStartDate(LocalDate contractStartDate) {
		this.contractStartDate = contractStartDate;
	}













	public LocalDate getContractEndDate() {
		return contractEndDate;
	}













	public void setContractEndDate(LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}













	public String getStatus() {
		return Status;
	}













	public void setStatus(String status) {
		Status = status;
	}













	public String getApproach() {
		return approach;
	}













	public void setApproach(String approach) {
		this.approach = approach;
	}













	public String getContractStatus() {
		return contractStatus;
	}













	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}













	@PrePersist
	@PreUpdate
	private void updateActiveStatus() {
		LocalDate currentDate = LocalDate.now();
		if((contractStartDate.isBefore(currentDate) || contractStartDate.isEqual(currentDate))&& (contractEndDate.isAfter(currentDate)||contractEndDate.isEqual(currentDate))){
			contractStatus = "active";
			
		
		}
		else {
			contractStatus = "inactive";
		}
	}
	
 
    // Getters and Setters
 
}
