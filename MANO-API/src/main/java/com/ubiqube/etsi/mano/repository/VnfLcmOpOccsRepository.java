package com.ubiqube.etsi.mano.repository;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;

public interface VnfLcmOpOccsRepository extends CrudRepository<VnfLcmOpOccs>, BinaryRepository {

	void save(List<VnfLcmOpOccs> vnfLcmOpOccsIds);

	/**
	 * It make sense only when file system is active TODO: Remove when RDBMS is
	 * deployed.
	 *
	 * @param vnfInstanceId
	 * @param operation
	 * @return
	 */
	@Nonnull
	VnfLcmOpOccs createLcmOpOccs(@Nonnull final UUID vnfInstanceId, final LcmOperationType operation);

	void updateState(@Nonnull final VnfLcmOpOccs lcmOpOccs, final LcmOperationStateType operationState);

	void attachProcessIdToLcmOpOccs(@Nonnull final String id, final String processId);
}
