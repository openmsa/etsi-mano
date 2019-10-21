package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfPackageRepository extends CrudRepository<VnfPkgInfo>, BinaryRepository {

	VnfLcmOpOcc createLcmOpOccs(String vnfInstanceId, LcmOperationType terminate);

	void updateState(VnfLcmOpOcc lcmOpOccs, LcmOperationStateType processing);

	void attachProcessIdToLcmOpOccs(@NotNull String id, String processId);
}
