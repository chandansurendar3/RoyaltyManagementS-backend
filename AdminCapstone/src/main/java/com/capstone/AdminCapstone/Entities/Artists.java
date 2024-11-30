package com.capstone.AdminCapstone.Entities;

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
    @Column(name = "phone_no")
    private String phoneNo;
    private String stageName;
    private String realName;
    private String country;
    @Column(name = "manager_id")
    private Long managerid;
    private boolean isDeleted = false; 

  

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	public Long getManagerid() {
		return managerid;
	}

	public void setManagerid(Long managerid) {
		this.managerid = managerid;
	}

	public Long getArtistid() {
        return artistid;
    }

    public void setArtistid(Long artistid) {
        this.artistid = artistid;
    }

 

    public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	@Override
	public String toString() {
		return "Artist [artistid=" + artistid + ", phoneNo=" + phoneNo + ", stageName=" + stageName + ", realName="
				+ realName + ", country=" + country + "]";
	}


     
}
