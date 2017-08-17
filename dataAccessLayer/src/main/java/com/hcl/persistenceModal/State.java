package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "state_id", unique = true, nullable = false)
	private String stateId;

	@Column(name = "state_name")
	private String stateName;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
