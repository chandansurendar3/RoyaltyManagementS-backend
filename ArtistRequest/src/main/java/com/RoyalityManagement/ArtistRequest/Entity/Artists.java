package com.RoyalityManagement.ArtistRequest.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artists {

    @Id
    @Column(name = "artist_id")
    private Long artistId;

    private String phone;
    private String stageName;
    private String realName;
    private String country;
    private Long manager_id;
	
	
	public Long getArtistId() {
		return artistId;
	}
	public void setArtistId(Long artistId) {
		this.artistId = artistId;
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
	public Long getManager_id() {
		return manager_id;
	}
	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "Artists [artistId=" + artistId + ", phone=" + phone + ", stageName=" + stageName + ", realName="
				+ realName + ", country=" + country + ", manager_id=" + manager_id + "]";
	}
	

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "artistid")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "managerid")
//    private User manager;

    // Getters and setters
//    public User getManager() {
//        return manager;
//    }
//
//    public void setManager(User manager) {
//        this.manager = manager;
//    }

}