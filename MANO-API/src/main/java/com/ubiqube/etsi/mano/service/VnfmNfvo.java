package com.ubiqube.etsi.mano.service;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

@Service
public class VnfmNfvo implements VnfmInterface {
	private final VnfInstanceLcm lcm;

	public VnfmNfvo(final VnfInstanceLcm _lcm) {
		lcm = _lcm;
	}

	@Override
	public VnfInstance createVnfInstance(final VnfPkgInfo vnf, final String vnfInstanceDescription, final String vnfInstanceName) {
		final CreateVnfRequest createVnfRequest = new CreateVnfRequest();
		createVnfRequest.setVnfdId(vnf.getId());
		createVnfRequest.setVnfInstanceDescription(vnfInstanceDescription);
		createVnfRequest.setVnfInstanceName(vnfInstanceName);
		return lcm.post(createVnfRequest);
	}

}
