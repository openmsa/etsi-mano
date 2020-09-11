package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.service.VnfLcmService;

public class VnfLcmController {
	private final VnfLcmService vnfLcmOpOccsRepository;
	private final VnfInstanceLcm vnfInstanceLcm;

	public VnfLcmController(final VnfLcmService _vnfLcmOpOccsRepository, final VnfInstanceLcm _vnfInstanceLcm) {
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
	}

	public List<VnfLcmOpOccs> vnfLcmOpOccsGet(final String filter) {
		return vnfLcmOpOccsRepository.query(filter);
	}

	public VnfLcmOpOccs vnfLcmOpOccsVnfLcmOpOccIdGet(final UUID id) {
		return vnfInstanceLcm.get(id);
	}
}
