package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;

public interface VnfPackageService {

	List<VnfComputeAspectDelta> findAspectDeltaByAspectId(final VnfCompute vnfCompute, final String aspectName);

	Optional<VnfStorage> findStorageByName(final VnfPackage vnfPackage, final String name);

	Optional<VnfVl> findVirtualLnkById(final UUID uuid);

	Optional<VnfStorage> findVirtualStorageById(final UUID uuid);

	Optional<VnfCompute> findComputeById(final UUID uuid);

	Optional<VnfExtCp> findExtCpById(final UUID uuid);

	VduInstantiationLevel findByVnfComputeAndInstantiationLevel(final VnfCompute x, final String scaleInfoName);

	VnfPackage findById(final VnfPackage vnfPackage);

	VnfPackage findById(final UUID vnfPkgId);

	List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfComputeAndLevel(final VnfPackage vnfPackage, final String level);

	List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfPacckage(final VnfPackage vnfPackage);

	VnfPackage save(final VnfPackage vnfPackage);

	Optional<VnfPackage> findByDescriptorId(final String descriptorId);

	Optional<VnfPackage> findByDescriptorIdFlavorIdVnfdVersion(final String descriptorId, final String flavorId, final String versionId);

	Optional<VnfPackage> findByDescriptorIdAndSoftwareVersion(final String name, final String version);

	Set<VnfLinkPort> findVnfVirtualLinks(final VnfPackage vnfPackage);

}
