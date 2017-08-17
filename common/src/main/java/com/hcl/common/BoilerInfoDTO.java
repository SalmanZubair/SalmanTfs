package com.hcl.common;

public class BoilerInfoDTO {

	private String boilerId;

	private String boilerName;

	private PlantInfoDTO plantInfo;

	public String getBoilerId() {
		return boilerId;
	}

	public String getBoilerName() {
		return boilerName;
	}

	public PlantInfoDTO getPlantInfo() {
		return plantInfo;
	}

	public void setBoilerId(String boilerId) {
		this.boilerId = boilerId;
	}

	public void setBoilerName(String boilerName) {
		this.boilerName = boilerName;
	}

	public void setPlantInfo(PlantInfoDTO plantInfo) {
		this.plantInfo = plantInfo;
	}
}
