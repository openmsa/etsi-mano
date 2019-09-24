package com.ubiqube.etsi.mano.service;

import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfmInterface {

	VnfInstance createVnfInstance(final VnfPkgInfo vnf, String vnfInstanceDescription, String vnfInstanceName);

}
