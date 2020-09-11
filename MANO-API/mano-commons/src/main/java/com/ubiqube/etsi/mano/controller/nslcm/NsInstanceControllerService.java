package com.ubiqube.etsi.mano.controller.nslcm;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nullable;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.model.NsInstantiate;

public interface NsInstanceControllerService {

	NsdInstance createNsd(String _nsdId, String nsName, String nsDescription);

	NsLcmOpOccs instantiate(UUID nsUuid, NsInstantiate req);

	NsLcmOpOccs terminate(UUID nsInstanceUuid, @Nullable OffsetDateTime terminationTime);

}