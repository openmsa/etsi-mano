package com.ubiqube.etsi.mano.repository;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;

public interface NsdRepository extends CrudRepository<NsDescriptorsNsdInfo>, BinaryRepository {
	void changeNsdUpdateState(NsDescriptorsNsdInfo nsdInfo, @Nonnull NsdUsageStateEnum inUse);

	NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(String nsInstanceId, @Nonnull LcmOperationTypeEnum instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull String lcmOpOccsId, String processId);
}
