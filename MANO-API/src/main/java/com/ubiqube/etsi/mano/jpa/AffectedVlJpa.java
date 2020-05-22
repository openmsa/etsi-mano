package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.AffectedVl;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;

public interface AffectedVlJpa extends CrudRepository<AffectedVl, UUID> {

	Optional<AffectedVl> findByVirtualLink(VnfVl vnfVl);

}
