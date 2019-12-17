package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.ubiqube.etsi.mano.dao.mano.common.Checksum;

@Embeddable
public class AdditionalArtifact {

	private String artifactPath;

	@Embedded
	private Checksum checksum;

	public String getArtifactPath() {
		return artifactPath;
	}

	public void setArtifactPath(final String artifactPath) {
		this.artifactPath = artifactPath;
	}

	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

}
