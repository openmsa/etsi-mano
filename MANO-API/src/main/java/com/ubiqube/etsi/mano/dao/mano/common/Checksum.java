package com.ubiqube.etsi.mano.dao.mano.common;

import javax.persistence.Embeddable;

@Embeddable
public class Checksum {
	private String algorithm;
	private String hash;

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(final String algorithm) {
		this.algorithm = algorithm;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(final String hash) {
		this.hash = hash;
	}

}
