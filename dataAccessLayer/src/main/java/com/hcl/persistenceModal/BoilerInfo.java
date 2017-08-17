package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "boiler_info")
public class BoilerInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "boiler_id", unique = true, nullable = false)
	private String boilerId;

	@Column(name = "boiler_name")
	private String boilerName;

	@ManyToOne
	@JoinColumn(name = "plant_id")
	private PlantInfo plantInfo;

	@ManyToOne
	@JoinColumn(name = "boiler_type_id")
	private BoilerType boilerType;

	@Column(name = "method_id")
	private String methodId;

	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(String boilerId) {
		this.boilerId = boilerId;
	}

	public String getBoilerName() {
		return boilerName;
	}

	public void setBoilerName(String boilerName) {
		this.boilerName = boilerName;
	}

	public PlantInfo getPlantInfo() {
		return plantInfo;
	}

	public void setPlantInfo(PlantInfo plantInfo) {
		this.plantInfo = plantInfo;
	}

	public BoilerType getBoilerType() {
		return boilerType;
	}

	public void setBoilerType(BoilerType boilerType) {
		this.boilerType = boilerType;
	}

}
