package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;

public interface NsInstanceRepository extends CrudRepository<NsInstancesNsInstance> {

	NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(String nsInstanceId, LcmOperationTypeEnum instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull String id, String processId);

}
