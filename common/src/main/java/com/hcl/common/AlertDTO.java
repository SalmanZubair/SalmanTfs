package com.hcl.common;

/**
 * Hello world!
 *
 */
public class AlertDTO {

	private String alertDesc;

	private String suggestion;

	private String insertDate;

	private int severity;
	
	public String getAlertDesc() {
		return alertDesc;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public void setAlertDesc(String alertDesc) {
		this.alertDesc = alertDesc;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
}
