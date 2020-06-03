package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;

public interface NsdPackageJpa extends CrudRepository<NsdPackage, UUID> {
	Optional<NsdPackage> findByNsdInvariantId(String nsdInvariantId);

	Set<NsdPackage> findByNestedNsdInfoIds_Parent(NsdPackage nsdPackage);
}
