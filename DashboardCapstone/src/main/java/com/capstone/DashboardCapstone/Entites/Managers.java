package com.capstone.DashboardCapstone.Entites;
 
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
    private String managerName;
    private boolean isDeleted;
    public Long getManagerId() {
		return managerId;
	}
 
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
 
	public String getCompany() {
		return company;
	}
	public String getManagerName() {
		return managerName;
	}
 
	public void setCompany(String company) {
		this.company = company;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
 
 
	public List<Artists> getArtists() {
		return artists;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted=isDeleted;
	}
	public boolean getIsDeleted() {
		return isDeleted;
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