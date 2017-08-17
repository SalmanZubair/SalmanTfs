package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_feed")
public class SensorFeed implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SensorFeedId sensorId;

	@Column(name = "attribute_value")
	private float attributeValue;
	
	@Column(name = "severity")
	private float severity;
	
	public float getSeverity() {
		return severity;
	}

	public void setSeverity(float severity) {
		this.severity = severity;
	}

	public SensorFeedId getSensorId() {
		return sensorId;
	}

	public void setSensorId(SensorFeedId sensorId) {
		this.sensorId = sensorId;
	}

	public float getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(float attributeValue) {
		this.attributeValue = attributeValue;
	}

	

}
