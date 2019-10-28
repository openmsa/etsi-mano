package com.ubiqube.etsi.mano.repository;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;

public interface NsLcmOpOccsRepository extends CrudRepository<NsLcmOpOccsNsLcmOpOcc>, BinaryRepository {

	NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(String nsInstanceId, @Nonnull LcmOperationTypeEnum instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull String lcmOpOccsId, String processId);
}
