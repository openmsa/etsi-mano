package com.ubiqube.etsi.mano.controller;

import java.time.OffsetDateTime;

public interface EtsiImplementation {

	String getVersion();

	boolean isDeprecated();

	OffsetDateTime getRetirementDate();
}
