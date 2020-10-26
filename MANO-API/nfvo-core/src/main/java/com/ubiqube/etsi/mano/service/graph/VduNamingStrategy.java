package com.ubiqube.etsi.mano.service.graph;

import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;

public interface VduNamingStrategy {

	String nameVdu(VnfLcmOpOccs vnfLcmOpOccs, String name, int count);

	String nameVdu(Blueprint plan, String toscaName, int count);
}
