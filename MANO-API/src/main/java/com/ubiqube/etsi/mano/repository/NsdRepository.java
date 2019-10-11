package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;

public interface NsdRepository extends CrudRepository<NsDescriptorsNsdInfo> {
	void changeNsdUpdateState(NsDescriptorsNsdInfo nsdInfo, NsdUsageStateEnum inUse);

	<T, U extends Class> T loadObject(@NotNull final String _id, final U t, final String _filename);

	NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(String nsInstanceId, LcmOperationTypeEnum instantiate);

	void attachProcessIdToLcmOpOccs(@NotNull String lcmOpOccsId, String processId);
}
