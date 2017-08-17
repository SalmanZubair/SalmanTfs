package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "efficiency_threshold")
public class EfficiencyThreshold implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EfficiencyThresholdId id;

	@Column(name = "threshold_percentage_en")
	private Float thresholdPercentageEn;

	@Column(name = "severity")
	private int severity;

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	
	public EfficiencyThresholdId getId() {
		return id;
	}

	public void setId(EfficiencyThresholdId id) {
		this.id = id;
	}

	public Float getThresholdPercentageEn() {
		return thresholdPercentageEn;
	}

	public void setThresholdPercentageEn(Float thresholdPercentageEn) {
		this.thresholdPercentageEn = thresholdPercentageEn;
	}

}
