package com.hcl.persistenceModal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alert")
public class Alert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "alert_id", unique = true, nullable = false)
	private int alertId;

	@Column(name = "alert_desc")
	private String alertDesc;

	@Column(name = "suggestion")
	private String suggestion;

	@Column(name = "status")
	private int status;

	@Column(name = "insert_date")
	private Timestamp insertDate;

	@Column(name = "resolve_date")
	private Timestamp resolveDate;

	@Column(name = "sensor_id")
	private String sensorId;
	
	
	@Column(name = "severity")
	private int severity;


	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public String getAlertDesc() {
		return alertDesc;
	}

	public void setAlertDesc(String alertDesc) {
		this.alertDesc = alertDesc;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public Timestamp getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(Timestamp resolveDate) {
		this.resolveDate = resolveDate;
	}

}
