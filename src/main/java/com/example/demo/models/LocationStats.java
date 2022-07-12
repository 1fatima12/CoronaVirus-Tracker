package com.example.demo.models;

public class LocationStats {
	private String state;
	private String contry;
	private int latestTotalCases;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContry() {
		return contry;
	}
	public void setContry(String contry) {
		this.contry = contry;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	public LocationStats() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", contry=" + contry + ", latestTotalCases=" + latestTotalCases + "]";
	}
	

}
