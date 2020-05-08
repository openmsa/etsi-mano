package com.ubiqube.etsi.mano.jpa.ipam;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.ipam.Networks;

public interface NetworksJpa extends CrudRepository<Networks, UUID> {

	@Query("SELECT n FROM Networks n WHERE vimResource IS NULL AND vimConnectionInformation.id = ?1")
	Optional<Networks> findFirstFreeNetwork(UUID vimConnUuid);
}
