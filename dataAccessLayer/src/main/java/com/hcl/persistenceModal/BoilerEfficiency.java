package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "boiler_efficiency")
public class BoilerEfficiency implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BoilerEfficiencyId efficiencyId;

	@Column(name = "attrib_percentage")
	private float attribPercentage;

	@Column(name = "severity")
	private int severity;

	@Column(name = "flag")
	private int flag;

	public float getAttribPercentage() {
		return attribPercentage;
	}

	public void setAttribPercentage(float attribPercentage) {
		this.attribPercentage = attribPercentage;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public BoilerEfficiencyId getEfficiencyId() {
		return efficiencyId;
	}

	public void setEfficiencyId(BoilerEfficiencyId efficiencyId) {
		this.efficiencyId = efficiencyId;
	}

}
