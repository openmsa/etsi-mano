package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Entity;

@Entity
public class GrantInformationExt extends GrantInformation {

	/** Serial. */
	private static final long serialVersionUID = 1L;
	private String externalId;

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

}
