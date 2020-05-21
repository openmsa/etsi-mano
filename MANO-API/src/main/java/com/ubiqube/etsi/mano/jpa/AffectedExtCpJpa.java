package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.AffectedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;

public interface AffectedExtCpJpa extends CrudRepository<AffectedExtCp, UUID> {

	Optional<AffectedExtCp> findByExtCp(VnfExtCp vnfExtCp);

}
