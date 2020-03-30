package com.ubiqube.etsi.mano.controller.lcmgrant;

import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.Grants;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;

public interface GrantManagement {
	@Nonnull
	Grants get(UUID grantId);

	@Nonnull
	Grants post(GrantRequest grant);

}
