package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class NsdUserDefinedData {
	private String name;

	private String value;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
