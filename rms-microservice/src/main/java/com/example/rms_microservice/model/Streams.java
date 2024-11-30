package com.example.rms_microservice.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Streams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamId;
    private LocalDate date;
    private Long songId;
    private Long streams;
    private Double royalty;
    private Boolean flag= true;
	public Long getStreamId() {
		return streamId;
	}
	public void setStreamId(Long streamId) {
		this.streamId = streamId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long getSongId() {
		return songId;
	}
	public void setSongId(Long songId) {
		this.songId = songId;
	}
	public Long getStreams() {
		return streams;
	}
	public void setStreams(Long streams) {
		this.streams = streams;
	}
	public Double getRoyalty() {
		return royalty;
	}
	public void setRoyalty(Double royalty) {
		this.royalty = royalty;
	}
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
