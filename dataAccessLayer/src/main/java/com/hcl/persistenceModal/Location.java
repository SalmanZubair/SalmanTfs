package com.hcl.persistenceModal;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "loc_id", unique = true, nullable = false)
	private String locationId;

	@Column(name = "loc_name")
	private String locationName;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@Column(name = "lat")
	private BigDecimal latitude;

	@Column(name = "long")
	private BigDecimal longitude;

	@Column(name = "address")
	private String address;

	@Column(name = "zip_code")
	private String zipCode;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
