package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.GrantInformation;

public interface GrantInformationJpa extends CrudRepository<GrantInformation, UUID> {
	// Nothing.
}
