package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "losses")
public class Losses implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "loss_id", unique = true, nullable = false)
	private String lossId;

	@ManyToOne
	@JoinColumn(name = "name")
	private EfficiencyFormula efficiencyFormula;

	@ManyToOne
	@JoinColumn(name = "boiler_id")
	private BoilerInfo boilerInfo;

	public String getLossId() {
		return lossId;
	}

	public void setLossId(String lossId) {
		this.lossId = lossId;
	}

	public BoilerInfo getBoilerInfo() {
		return boilerInfo;
	}

	public void setBoilerInfo(BoilerInfo boilerInfo) {
		this.boilerInfo = boilerInfo;
	}

	public EfficiencyFormula getEfficiencyFormula() {
		return efficiencyFormula;
	}

	public void setEfficiencyFormula(EfficiencyFormula efficiencyFormula) {
		this.efficiencyFormula = efficiencyFormula;
	}
}
