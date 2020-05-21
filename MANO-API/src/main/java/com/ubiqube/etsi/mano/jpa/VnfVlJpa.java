package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfVl;

public interface VnfVlJpa extends CrudRepository<VnfVl, UUID> {

	// int countByVnfInstanceAndVduId(VnfInstance vnfInstance, UUID id);

}
