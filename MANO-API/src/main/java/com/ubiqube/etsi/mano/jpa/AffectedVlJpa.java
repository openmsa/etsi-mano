package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.AffectedVl;

public interface AffectedVlJpa extends CrudRepository<AffectedVl, UUID> {

	Optional<AffectedVl> findByVirtualLinkDescId(UUID uuid);

}
