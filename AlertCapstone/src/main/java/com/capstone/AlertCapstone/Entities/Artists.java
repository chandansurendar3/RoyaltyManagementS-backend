package com.capstone.AlertCapstone.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Artists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    private String phone;
    private String stageName;
    private String realName;

    public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Artists [artistId=" + artistId + ", phone=" + phone + ", stageName=" + stageName + ", realName="
				+ realName + ", manager=" + manager + "]";
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

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}

	@ManyToOne
    @JoinColumn(name = "manager_id")
    private Managers manager;
}
