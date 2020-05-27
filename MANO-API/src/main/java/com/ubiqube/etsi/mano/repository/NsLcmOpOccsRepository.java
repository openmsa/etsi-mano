package com.ubiqube.etsi.mano.repository;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;

public interface NsLcmOpOccsRepository extends CrudRepository<NsLcmOpOccs> {

	NsLcmOpOccs createLcmOpOccs(NsdInstance nsInstance, @Nonnull NsLcmOpType instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull UUID lcmOpOccsId, String processId);

}
