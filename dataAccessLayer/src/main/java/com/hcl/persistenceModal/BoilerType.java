package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "boiler_type")
public class BoilerType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "boiler_type_id", unique = true, nullable = false)
	private String boilerTypeId;

	@Column(name = "boiler_type_name")
	private String boilerTypeName;

	public String getBoilerTypeId() {
		return boilerTypeId;
	}

	public void setBoilerTypeId(String boilerTypeId) {
		this.boilerTypeId = boilerTypeId;
	}

	public String getBoilerTypeName() {
		return boilerTypeName;
	}

	public void setBoilerTypeName(String boilerTypeName) {
		this.boilerTypeName = boilerTypeName;
	}

	
	
}
