package com.capstone.DashboardCapstone.Entites;

import java.sql.Date;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Streams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamId;

    private Date date;
    private Long streams;
    private Double royalty;
    private Boolean flag;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Songs song;

	public Long getStreamId() {
		return streamId;
	}

	public void setStreamId(Long streamId) {
		this.streamId = streamId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Songs getSong() {
		return song;
	}

	public void setSong(Songs song) {
		this.song = song;
	}

	@Override
	public String toString() {
		return "Streams [streamId=" + streamId + ", date=" + date + ", streams=" + streams + ", royalty=" + royalty
				+ ", flag=" + flag + ", song=" + song + "]";
	}
}
