package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.AffectedCompute;

public interface AffectedComputeJpa extends CrudRepository<AffectedCompute, UUID> {

	Optional<AffectedCompute> findByVnfComputeIdAndVnfLcmOpOccsId(UUID id, UUID id2);

}
