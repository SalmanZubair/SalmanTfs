package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plant_info")
public class PlantInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "plant_id", unique = true, nullable = false)
	private String plantId;

	@Column(name = "plant_name")
	private String plantName;

	@ManyToOne
	@JoinColumn(name = "loc_id")
	private Location location;

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
