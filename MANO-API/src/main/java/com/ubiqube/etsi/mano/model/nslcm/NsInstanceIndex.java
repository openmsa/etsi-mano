package com.ubiqube.etsi.mano.model.nslcm;

import java.util.HashSet;
import java.util.Set;

public class NsInstanceIndex {
	private Set<String> lcmOpOccsIndex = new HashSet<>();

	public Set<String> getLcmOpOccsIndex() {
		return lcmOpOccsIndex;
	}

	public void setLcmOpOccsIndex(final Set<String> lcmOpOccsIndex) {
		this.lcmOpOccsIndex = lcmOpOccsIndex;
	}

	public void addLcmOpOccs(final String lcmOpOccsId) {
		lcmOpOccsIndex.add(lcmOpOccsId);
	}
}
