package com.ubiqube.etsi.mano.model.nslcm;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;

public class NsInstanceIndex {
	private Map<String, NsLcmOpOccsIndex> lcmOpOccsIndex = new HashMap<>();

	public Map<String, NsLcmOpOccsIndex> getLcmOpOccsIndex() {
		return lcmOpOccsIndex;
	}

	public void setLcmOpOccsIndex(final Map<String, NsLcmOpOccsIndex> lcmOpOccsIndex) {
		this.lcmOpOccsIndex = lcmOpOccsIndex;
	}

	public void addLcmOpOccs(final NsLcmOpOccsIndex lcmOpOccs) {
		lcmOpOccsIndex.put(lcmOpOccs.getLcmOpOccsId(), lcmOpOccs);
	}

	public NsLcmOpOccsIndex getLcmOpOccs(@NotNull final String lcmOpOccsId) {
		return lcmOpOccsIndex.get(lcmOpOccsId);
	}

	public void addLcmOpOccs(final NsLcmOpOcc lcmOpOccs) {
		final NsLcmOpOccsIndex lcmIdx = new NsLcmOpOccsIndex();
		lcmIdx.setLcmOpOccsId(lcmOpOccs.getId());
		lcmOpOccsIndex.put(lcmOpOccs.getId(), lcmIdx);
	}
}
