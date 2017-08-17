package com.hcl.persistenceModal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "efficiency_formula")
public class EfficiencyFormula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "formula")
	private String formula;

	@Column(name = "description")
	private String description;

	@Column(name = "params")
	private String params;
	
	@Column(name = "insert_date")
	private Timestamp insertDate;

	@Column(name = "method_id")
	private String methodId;
	
	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public String getMethodId() {
		return methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
