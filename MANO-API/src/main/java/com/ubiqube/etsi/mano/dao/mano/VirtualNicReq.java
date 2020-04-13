package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class VirtualNicReq {

	private String name;
	private String description;
	private boolean supportMandatory;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public boolean isSupportMandatory() {
		return supportMandatory;
	}

	public void setSupportMandatory(final boolean supportMandatory) {
		this.supportMandatory = supportMandatory;
	}

}
