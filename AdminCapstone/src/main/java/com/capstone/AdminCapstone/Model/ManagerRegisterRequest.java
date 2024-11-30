package com.capstone.AdminCapstone.Model;

public class ManagerRegisterRequest {

	private String username;
	private String password;
	private String emailid;
	private String managerName;
	private String company;
	private Long artistId;
	private Integer managerpct;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Long getArtistId() {
		return artistId;
	}
	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}
	public Integer getManagerpct() {
		return managerpct;
	}
	public void setManagerpct(Integer managerpct) {
		this.managerpct = managerpct;
	}
	
	
	
	
}
