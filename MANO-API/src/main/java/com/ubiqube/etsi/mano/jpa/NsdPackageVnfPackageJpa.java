package com.ubiqube.etsi.mano.jpa;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;

public interface NsdPackageVnfPackageJpa extends CrudRepository<NsdPackageVnfPackage, UUID> {
	Set<NsdPackageVnfPackage> findByNsdPackage(NsdPackage nsdPackage);
}
