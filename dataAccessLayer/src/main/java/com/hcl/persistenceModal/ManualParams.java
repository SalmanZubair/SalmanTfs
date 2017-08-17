package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "manual_params")
public class ManualParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ManualParamsId paramId;

	@Column(name = "param_name")
	private String paramName;

	@Column(name = "param_value")
	private String paramValue;

	@Column(name = "display_name")
	private String displayName;
	
	@ManyToOne
	@JoinColumn(name = "boiler_id")
	private BoilerInfo boilerInfo;
	
	

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public ManualParamsId getParamId() {
		return paramId;
	}

	public void setParamId(ManualParamsId paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public BoilerInfo getBoilerInfo() {
		return boilerInfo;
	}

	public void setBoilerInfo(BoilerInfo boilerInfo) {
		this.boilerInfo = boilerInfo;
	}

}
