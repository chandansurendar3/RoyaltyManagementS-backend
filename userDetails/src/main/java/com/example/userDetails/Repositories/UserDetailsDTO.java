package com.example.userDetails.Repositories;

import java.util.List;

import com.example.userDetails.model.Artist;
import com.example.userDetails.model.Manager;
import com.example.userDetails.model.User;

public class UserDetailsDTO {
    private User user;
    private Artist artist;
    private Manager manager;
    private String userType;
    private List<Artist> artists;
    private String managerName;
    
    
//	public String getManagerName() {
//		return managerName;
//	}
//	public void setManagerName(String managerName) {
//		this.managerName = managerName;
//	}
	
	
	public UserDetailsDTO(User user, Artist artist, Manager manager, String managerName) {
	super();
	this.user = user;
	this.artist = artist;
	this.manager = manager;
	this.managerName = managerName;
}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	
//	public UserDetailsDTO(User user, Artist artist, Manager manager, String userType) {
//		super();
//		this.user = user;
//		this.artist = artist;
//		this.manager = manager;
//		this.userType = userType;
//        this.managerName = (user != null ) ? user.getUsername() : null;
//
//
//	}
//	 public UserDetailsDTO(User user, Artist artist, Manager manager, String userType, List<Artist> artists) {
//	        this.user = user;
//	        this.artist = artist;
//	        this.manager = manager;
//	        this.userType = userType;
//	        this.artists = artists;
//	    }
	public String getManagerName() {
		return managerName;
	}

	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

    // Constructor, getters, and setters
    
}