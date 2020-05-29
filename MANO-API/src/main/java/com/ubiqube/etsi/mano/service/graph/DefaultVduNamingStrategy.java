package com.ubiqube.etsi.mano.service.graph;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;

@Service
public class DefaultVduNamingStrategy implements VduNamingStrategy {

	@Override
	public String nameVdu(final VnfLcmOpOccs vnfLcmOpOccs, final String name, final int count) {
		final String vnfInstanceId = vnfLcmOpOccs.getVnfInstance().getId().toString();
		return vnfInstanceId.substring(0, 8) + '-' + name + '-' + String.format("%04d", count);
	}

}
