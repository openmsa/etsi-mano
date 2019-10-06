package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;

public interface NsInstanceRepository extends CrudRepository<NsInstance> {

	NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(String nsInstanceId, LcmOperationTypeEnum instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull String lcmOpOccsId, String processId);

	void changeNsdUpdateState(NsInstance nsInstance, NsStateEnum notInstantiated);

}
