package com.hcl.common;

public class LocationDTO {

	private String locationName;

	private StateDTO state;
	
	public String getLocationName() {
		return locationName;
	}

	public StateDTO getState() {
		return state;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setState(StateDTO state) {
		this.state = state;
	}

}
