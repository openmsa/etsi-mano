package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest.TerminationTypeEnum;

import ma.glasnost.orika.MapperFacade;

@Service
public class VnfmNfvo implements VnfmInterface {
	private final VnfInstanceLcm lcm;
	private final MapperFacade mapper;

	public VnfmNfvo(final VnfInstanceLcm _lcm, final MapperFacade _mapper) {
		lcm = _lcm;
		mapper = _mapper;
	}

	@Override
	public VnfInstance createVnfInstance(final VnfPackage vnf, final String vnfInstanceDescription, final String vnfInstanceName) {
		final CreateVnfRequest createVnfRequest = new CreateVnfRequest();
		createVnfRequest.setVnfdId(vnf.getId().toString());
		createVnfRequest.setVnfInstanceDescription(vnfInstanceDescription);
		createVnfRequest.setVnfInstanceName(vnfInstanceName);
		final com.ubiqube.etsi.mano.model.nslcm.VnfInstance inst = lcm.post(createVnfRequest);
		return mapper.map(inst, VnfInstance.class);
	}

	@Override
	public VnfLcmOpOccs vnfInstatiate(final UUID vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest, final UUID vnfId) {
		return lcm.instantiate(vnfInstanceId, instantiateVnfRequest);
	}

	@Override
	public VnfLcmOpOccs getVnfLcmOpOccs(@NotNull final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfLcmOpOccs vnfTerminate(final UUID nsInstanceId) {
		final TerminateVnfRequest terminateVnfRequest = new TerminateVnfRequest();
		terminateVnfRequest.setTerminationType(TerminationTypeEnum.FORCEFUL);
		return lcm.terminate(nsInstanceId, terminateVnfRequest);
	}

}
