package com.ubiqube.etsi.mano.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.controller.nslcm.sol003.Sol003LcmLinkable;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
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

	@Override
	public VnfLcmOpOcc vnfInstatiate(final String vnfInstanceId, final String vnfId) {
		lcm.instantiate(vnfInstanceId, null, new Sol003LcmLinkable());
		// TODO It's a little more complex, we need to subscribe and wait for the URL to
		// be called.
		// Or we may need an other way.
		return null;
	}

	@Override
	public VnfLcmOpOcc getVnfLcmOpOccs(@NotNull final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfLcmOpOcc vnfTerminate(final String nsInstanceId, final String vnfId) {
		// TODO Auto-generated method stub
		return null;
	}

}
