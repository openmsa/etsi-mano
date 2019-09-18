package com.ubiqube.api.entities.orchestration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceId {
	private Long id;
	private String name;
	private String serviceExternalReference;
	private String state;

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@JsonProperty("serviceExternalReference")
	public String getServiceExternalReference() {
		return serviceExternalReference;
	}

	public void setServiceExternalReference(final String serviceExternalReference) {
		this.serviceExternalReference = serviceExternalReference;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ServiceId [id=" + id + ", name=" + name + ", serviceExternalReference=" + serviceExternalReference + ", state=" + state + "]";
	}

}
