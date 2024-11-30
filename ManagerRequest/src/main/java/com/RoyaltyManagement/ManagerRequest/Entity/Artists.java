package com.RoyaltyManagement.ManagerRequest.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artists {

    @Id
    @Column(name = "artist_id")
    private Long artistid;

    private String phone;
    private String stageName;
    private String realName;
    private String country;
    private Long managerId;
	public Long getArtistid() {
		return artistid;
	}
	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	@Override
	public String toString() {
		return "Artists [artistid=" + artistid + ", phone=" + phone + ", stageName=" + stageName + ", realName="
				+ realName + ", country=" + country + ", managerId=" + managerId + "]";
	}
	         
}
