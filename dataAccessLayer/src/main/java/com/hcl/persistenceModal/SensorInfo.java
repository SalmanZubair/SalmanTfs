package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_info")
public class SensorInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "sensor_id", unique = true, nullable = false)
	private String sensorId;

	@ManyToOne
	@JoinColumn(name = "boiler_id")
	private BoilerInfo boilerInfo;

	@Column(name = "sensor_type")
	private String sensortype;

	@Column(name = "method_id")
	private String methodId;

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public BoilerInfo getBoilerInfo() {
		return boilerInfo;
	}

	public void setBoilerInfo(BoilerInfo boilerInfo) {
		this.boilerInfo = boilerInfo;
	}

	public String getSensortype() {
		return sensortype;
	}

	public void setSensortype(String sensortype) {
		this.sensortype = sensortype;
	}

}
