package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;

public interface GrantRequestJpa extends CrudRepository<GrantsRequest, UUID> {
	// Nothing.
}
