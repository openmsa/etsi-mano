package com.ubiqube.etsi.mano.vnfm.v331;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.EtsiImplementation;

@Service
public class Vnfm331Implementation implements EtsiImplementation {

	@Override
	public String getVersion() {
		return "3.3.1";
	}

	@Override
	public boolean isDeprecated() {
		return false;
	}

	@Override
	public OffsetDateTime getRetirementDate() {
		return null;
	}

}
