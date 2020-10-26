package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;

@Service
public class VnfmNfvo implements VnfmInterface {
	private final VnfInstanceLcm lcm;

	public VnfmNfvo(final VnfInstanceLcm _lcm) {
		lcm = _lcm;
	}

	@Override
	public VnfInstance createVnfInstance(final VnfPackage vnf, final String vnfInstanceDescription, final String vnfInstanceName) {
		return lcm.post(vnf.getId().toString(), vnfInstanceName, vnfInstanceDescription);
	}

	@Override
	public Blueprint vnfInstatiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest, final UUID vnfId) {
		return lcm.instantiate(vnfInstanceId, instantiateVnfRequest);
	}

	@Override
	public Blueprint getVnfLcmOpOccs(@NotNull final UUID id) {
		return lcm.get(id);
	}

	@Override
	public Blueprint vnfTerminate(final UUID nsInstanceId) {
		return lcm.terminate(nsInstanceId, CancelModeTypeEnum.FORCEFUL, null);
	}

}
