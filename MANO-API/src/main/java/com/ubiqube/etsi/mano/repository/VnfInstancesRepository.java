package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;

public interface VnfInstancesRepository extends CrudRepository<VnfInstance> {

	<T, U extends Class> T loadObject(@NotNull final String _id, final U t, final String _filename);

	VnfLcmOpOcc createLcmOpOccs(String vnfInstanceId, LcmOperationTypeEnum terminate);

	void updateState(VnfLcmOpOcc lcmOpOccs, LcmOperationStateType processing);

	void attachProcessIdToLcmOpOccs(@NotNull String id, String processId);

}
