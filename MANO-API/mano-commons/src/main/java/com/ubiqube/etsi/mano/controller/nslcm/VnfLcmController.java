package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;

public interface VnfLcmController {

	List<Blueprint> vnfLcmOpOccsGet(final String filter);

	Blueprint vnfLcmOpOccsVnfLcmOpOccIdGet(final UUID id);
}