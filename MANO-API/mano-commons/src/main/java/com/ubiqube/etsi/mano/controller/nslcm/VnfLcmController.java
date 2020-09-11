package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;

public interface VnfLcmController {

	List<VnfLcmOpOccs> vnfLcmOpOccsGet(final String filter);

	VnfLcmOpOccs vnfLcmOpOccsVnfLcmOpOccIdGet(final UUID id);
}