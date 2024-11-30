package com.capstone.AdminCapstone.Model;

public class ArtistRegistrationRequest {

	private String username;
	private String password;
	private String emailid;
	private String stageName;
	private String realName;
	private String country;
	private String phoneNo;
	private Long managerId;
	
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "ArtistRegistrationRequest [username=" + username + ", password=" + password + ", emailid=" + emailid
				+ ", stageName=" + stageName + ", realName=" + realName + ", country=" + country + ", phoneNo="
				+ phoneNo + ", managerId=" + managerId + "]";
	}
     
     
}
