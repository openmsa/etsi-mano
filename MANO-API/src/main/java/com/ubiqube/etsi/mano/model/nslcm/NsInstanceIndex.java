package com.ubiqube.etsi.mano.model.nslcm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;

public class NsInstanceIndex {
	private Map<UUID, NsLcmOpOccsIndex> lcmOpOccsIndex = new HashMap<>();

	public Map<UUID, NsLcmOpOccsIndex> getLcmOpOccsIndex() {
		return lcmOpOccsIndex;
	}

	public void setLcmOpOccsIndex(final Map<UUID, NsLcmOpOccsIndex> lcmOpOccsIndex) {
		this.lcmOpOccsIndex = lcmOpOccsIndex;
	}

	public void addLcmOpOccs(final NsLcmOpOccsIndex lcmOpOccs) {
		lcmOpOccsIndex.put(UUID.fromString(lcmOpOccs.getLcmOpOccsId()), lcmOpOccs);
	}

	public NsLcmOpOccsIndex getLcmOpOccs(@NotNull final UUID lcmOpOccsId) {
		return lcmOpOccsIndex.get(lcmOpOccsId);
	}

	public void addLcmOpOccs(final NsLcmOpOcc lcmOpOccs) {
		final NsLcmOpOccsIndex lcmIdx = new NsLcmOpOccsIndex();
		lcmIdx.setLcmOpOccsId(lcmOpOccs.getId());
		lcmOpOccsIndex.put(UUID.fromString(lcmOpOccs.getId()), lcmIdx);
	}
}
