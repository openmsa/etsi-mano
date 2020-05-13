package com.ubiqube.etsi.mano.repository;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;

public interface NsLcmOpOccsRepository extends CrudRepository<NsLcmOpOcc> {

	NsLcmOpOcc createLcmOpOccs(UUID nsInstanceId, @Nonnull NsLcmOpType instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull UUID lcmOpOccsId, String processId);

}
