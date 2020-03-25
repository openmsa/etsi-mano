package com.ubiqube.etsi.mano.service;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfmInterface {

	VnfInstance createVnfInstance(final VnfPkgInfo vnf, String vnfInstanceDescription, String vnfInstanceName);

	VnfLcmOpOcc vnfInstatiate(String vnfInstanceId, String vnfId);

	VnfLcmOpOcc getVnfLcmOpOccs(@NotNull String id);

	VnfLcmOpOcc vnfTerminate(String nsInstanceId, String vnfId);

}
