package com.ubiqube.etsi.mano.repository;

import java.util.List;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;

public interface VnfLcmOpOccsRepository extends CrudRepository<VnfLcmOpOcc>, BinaryRepository {

	void save(List<VnfLcmOpOcc> vnfLcmOpOccsIds);

	/**
	 * It make sense only when file system is active TODO: Remove when RDBMS is
	 * deployed.
	 *
	 * @param vnfInstanceId
	 * @param operation
	 * @return
	 */
	VnfLcmOpOcc createLcmOpOccs(@Nonnull final String vnfInstanceId, final LcmOperationType operation);

	void updateState(@Nonnull final VnfLcmOpOcc lcmOpOccs, final LcmOperationStateType operationState);

	void attachProcessIdToLcmOpOccs(@Nonnull final String id, final String processId);
}
