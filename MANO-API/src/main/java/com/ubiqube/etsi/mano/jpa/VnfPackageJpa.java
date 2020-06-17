package com.ubiqube.etsi.mano.jpa;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public interface VnfPackageJpa extends CrudRepository<VnfPackage, UUID> {

	Optional<VnfPackage> findByDescriptorId(String descriptorId);

	Set<VnfPackage> findByNsdPackages_NsdPackage_Id(UUID nsdInfo);

	Optional<VnfPackage> findByDescriptorIdAndVnfSoftwareVersion(String name, String version);

	Optional<VnfPackage> findByDescriptorIdAndFlavorIdAndVnfdVersion(String descriptorId, String flavorId, String versionId);
}
