package com.ubiqube.etsi.mano.repository;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public interface VnfPackageRepository extends CrudRepository<VnfPackage>, BinaryRepository {

	// VnfLcmOpOcc createLcmOpOccs(String vnfInstanceId, LcmOperationType
	// terminate);

	// void updateState(VnfLcmOpOcc lcmOpOccs, LcmOperationStateType processing);

	// void attachProcessIdToLcmOpOccs(@NotNull String id, String processId);
}
