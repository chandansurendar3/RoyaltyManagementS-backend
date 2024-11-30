package com.capstone.AlertCapstone.Modules;

import java.util.List;

public class NotificationResponse {
    private String request;
    private List<String> payout;
    private String streams;
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public List<String> getPayout() {
		return payout;
	}
	public void setPayout(List<String> payout) {
		this.payout = payout;
	}
	public String getStreams() {
		return streams;
	}
	public void setStreams(String streams) {
		this.streams = streams;
	}
	@Override
	public String toString() {
		return "NotificationResponse [request=" + request + ", payout=" + payout + ", streams=" + streams + "]";
	}
    
}
