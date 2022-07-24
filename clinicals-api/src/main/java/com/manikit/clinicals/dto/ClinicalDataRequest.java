package com.manikit.clinicals.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClinicalDataRequest {

	@JsonProperty
	private String componentName;
	@JsonProperty
	private String componentValue;
	@JsonProperty
	private int patientId;

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(String componentValue) {
		this.componentValue = componentValue;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

}
