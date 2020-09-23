package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.service.VnfLcmService;

@Service
public class VnfLcmControllerImpl implements VnfLcmController {
	private final VnfLcmService vnfLcmOpOccsRepository;
	private final VnfInstanceLcm vnfInstanceLcm;

	public VnfLcmControllerImpl(final VnfLcmService _vnfLcmOpOccsRepository, final VnfInstanceLcm _vnfInstanceLcm) {
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
	}

	@Override
	public List<VnfLcmOpOccs> vnfLcmOpOccsGet(final String filter) {
		return vnfLcmOpOccsRepository.query(filter);
	}

	@Override
	public VnfLcmOpOccs vnfLcmOpOccsVnfLcmOpOccIdGet(final UUID id) {
		return vnfInstanceLcm.get(id);
	}
}
