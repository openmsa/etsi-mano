package com.ubiqube.etsi.mano.service.graph;

import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;

public interface VduNamingStrategy {

	String nameVdu(VnfLcmOpOccs vnfLcmOpOccs, String name, int count);
}
