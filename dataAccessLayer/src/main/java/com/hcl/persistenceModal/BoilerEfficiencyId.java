package com.hcl.persistenceModal;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class BoilerEfficiencyId implements Serializable{

	/**
	 * @author salman.z
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "boiler_id")
	private String boilerId;

	@Column(name = "insert_date")
	private Timestamp insertDate;

	@Column(name = "attrib_id")
	private String attribId;

	public String getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(String boilerId) {
		this.boilerId = boilerId;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public String getAttribId() {
		return attribId;
	}

	public void setAttribId(String attribId) {
		this.attribId = attribId;
	}
			
}