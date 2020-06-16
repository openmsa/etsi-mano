package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;

public interface NsdPackageJpa extends CrudRepository<NsdPackage, UUID> {
	Optional<NsdPackage> findByNsdInvariantId(String nsdInvariantId);

	@Query("select child \n" +
			" 	from NsdPackageNsdPackage nsdpackage0_\n" +
			" 	left outer join NsdPackage child on nsdpackage0_.child = child \n" +
			" 	where parent_id = ?1 ")
	Set<NsdPackage> findByNestedNsdInfoIds_Parent(NsdPackage nsdPackage);
}
