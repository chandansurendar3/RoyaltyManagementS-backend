package com.capstone.AlertCapstone.Entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Songs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @Override
	public String toString() {
		return "Songs [songId=" + songId + ", title=" + title + ", releaseDate=" + releaseDate + ", collaborationPct=" + collaborationPct + ", genre=" + genre + ", country=" + country
				+ ", artist=" + artist + "]";
	}

	private String title;
    private Date releaseDate;
    private Double collaborationPct;
    private String genre;
    private String country;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artists artist;

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public Double getCollaborationPct() {
		return collaborationPct;
	}

	public void setCollaborationPct(Double collaborationPct) {
		this.collaborationPct = collaborationPct;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Artists getArtist() {
		return artist;
	}

	public void setArtist(Artists artist) {
		this.artist = artist;
	}
}