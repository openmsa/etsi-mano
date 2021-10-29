package com.ubiqube.etsi.mano.vnfm.v351;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.EtsiImplementation;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class Nfvo351Implementation implements EtsiImplementation {

	@Override
	public String getVersion() {
		return "3.5.1";
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
