package com.hcl.common;

public class PlantInfoDTO {

	private String plantId;

	private String plantName;

	private LocationDTO location;
	
	public String getPlantId() {
		return plantId;
	}

	public String getPlantName() {
		return plantName;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

}
