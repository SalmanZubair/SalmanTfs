package com.hcl.persistenceModal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "measurement_method")
public class MeasurementMethod implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "method_id", unique = true, nullable = false)
	private String methodId;
	

	@Column(name = "method_name")
	private String methodName;


	public String getMethodId() {
		return methodId;
	}


	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}


	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
