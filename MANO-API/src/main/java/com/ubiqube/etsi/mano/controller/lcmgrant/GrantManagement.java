package com.ubiqube.etsi.mano.controller.lcmgrant;

import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;

public interface GrantManagement {
	@Nonnull
	GrantResponse get(UUID grantId);

	@Nonnull
	GrantResponse post(GrantRequest grant);

}
