package com.hcl.persistenceModal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EfficiencyThresholdId implements Serializable {

	/**
	 * @author salman.z
	 */
	private static final long serialVersionUID = 1L;


	@Column(name = "threshold_percentage_st")
	private Float thresholdPercentageSt;

	@Column(name = "boiler_id")
	private String boilerId;

	@Column(name = "parameter")
	private String parameter;

	@Column(name = "insert_date")
	private Timestamp insertDate;

	public Float getThresholdPercentageSt() {
		return thresholdPercentageSt;
	}

	public void setThresholdPercentageSt(Float thresholdPercentageSt) {
		this.thresholdPercentageSt = thresholdPercentageSt;
	}

	public String getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(String boilerId) {
		this.boilerId = boilerId;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	
}