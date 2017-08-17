package com.hcl.persistenceModal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SensorFeedId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "sensor_id")
	private String sensorId;

	@Column(name = "sensor_attribute")
	private String sensorAttribute;

	@Column(name = "insert_date")
	private Timestamp insertDate;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getSensorAttribute() {
		return sensorAttribute;
	}

	public void setSensorAttribute(String sensorAttribute) {
		this.sensorAttribute = sensorAttribute;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

}