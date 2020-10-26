package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;

public interface BlueprintJpa extends CrudRepository<Blueprint, UUID> {

	/// void find
}
