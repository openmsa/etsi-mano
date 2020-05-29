package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;

public interface NsInstantiatedBaseJpa extends CrudRepository<NsInstantiatedBase, UUID> {
	// Nothing.
}
