package com.capstone.AlertCapstone.Entities;

import com.capstone.AlertCapstone.Entities.Enums.Approach;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RoyaltyContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artists artist;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Managers manager;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Songs song;


    @Enumerated(EnumType.STRING)
    @Column(name = "approach")
    private Approach approached;

    private String status;
    private Boolean flag;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Artists getArtist() {
		return artist;
	}

	public void setArtist(Artists artist) {
		this.artist = artist;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}

	public Songs getSong() {
		return song;
	}

	public void setSong(Songs song) {
		this.song = song;
	}



	public Approach getApproached() {
		return approached;
	}

	public void setApproached(Approach approached) {
		this.approached = approached;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "RoyaltyContract [contractId=" + contractId + ", artist=" + artist + ", manager=" + manager + ", song="
				+ song + ", approached=" + approached + ", Status=" + status + "]";
	}

    
}
