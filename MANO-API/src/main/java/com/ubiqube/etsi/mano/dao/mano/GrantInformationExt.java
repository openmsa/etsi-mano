package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Entity;

@Entity
public class GrantInformationExt extends GrantInformation {

	private String externalId;

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

}
