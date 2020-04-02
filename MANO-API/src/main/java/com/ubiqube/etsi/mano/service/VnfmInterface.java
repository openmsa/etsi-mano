package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfmInterface {

	VnfInstance createVnfInstance(final VnfPkgInfo vnf, String vnfInstanceDescription, String vnfInstanceName);

	VnfLcmOpOccs vnfInstatiate(String vnfInstanceId, String vnfId);

	VnfLcmOpOccs getVnfLcmOpOccs(@NotNull UUID id);

	VnfLcmOpOccs vnfTerminate(String nsInstanceId, String vnfId);

}
