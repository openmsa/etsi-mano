package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfComputeAspectDeltaJpa;
import com.ubiqube.etsi.mano.jpa.VnfComputeJpa;
import com.ubiqube.etsi.mano.jpa.VnfExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiationLevelsJpa;
import com.ubiqube.etsi.mano.jpa.VnfLinkPortJpa;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.jpa.VnfStorageJpa;
import com.ubiqube.etsi.mano.jpa.VnfVlJpa;

@Service
public class VnfPackageServiceImpl implements VnfPackageService {
	private final VnfComputeAspectDeltaJpa vnfComputeAspectDeltaJpa;

	private final VnfStorageJpa vnfStorageJpa;

	private final VnfVlJpa vnfVl;

	private final VnfComputeJpa vnfComputeJpa;

	private final VnfExtCpJpa vnfExtCpJpa;

	private final VnfPackageJpa vnfPackageJpa;

	private final VnfInstantiationLevelsJpa vnfInstantiationLevelsJpa;

	private final VnfLinkPortJpa vnfLinkPortJpa;

	public VnfPackageServiceImpl(final VnfComputeAspectDeltaJpa _vnfComputeAspectDeltaJpa, final VnfStorageJpa _vnfStorageJpa, final VnfVlJpa _vnfVl, final VnfComputeJpa _vnfComputeJpa, final VnfExtCpJpa _vnfExtCpJpa, final VnfPackageJpa _vnfPackageJpa, final VnfInstantiationLevelsJpa _vnfInstantiationLevelsJpa, final VnfLinkPortJpa _vnfLinkPortJpa) {
		vnfComputeAspectDeltaJpa = _vnfComputeAspectDeltaJpa;
		vnfStorageJpa = _vnfStorageJpa;
		vnfVl = _vnfVl;
		vnfComputeJpa = _vnfComputeJpa;
		vnfExtCpJpa = _vnfExtCpJpa;
		vnfPackageJpa = _vnfPackageJpa;
		vnfInstantiationLevelsJpa = _vnfInstantiationLevelsJpa;
		vnfLinkPortJpa = _vnfLinkPortJpa;
	}

	@Override
	public List<VnfComputeAspectDelta> findAspectDeltaByAspectId(final VnfCompute vnfCompute, final String aspectName) {
		return vnfComputeAspectDeltaJpa.findByVnfComputeAndAspectName(vnfCompute, aspectName);
	}

	@Override
	public Optional<VnfStorage> findStorageByName(final VnfPackage vnfPackage, final String name) {
		return vnfStorageJpa.findOneByVnfPackageAndToscaName(vnfPackage, name);
	}

	@Override
	public Optional<VnfVl> findVirtualLnkById(final UUID uuid) {
		return vnfVl.findById(uuid);
	}

	@Override
	public Optional<VnfStorage> findVirtualStorageById(final UUID uuid) {
		return vnfStorageJpa.findById(uuid);
	}

	@Override
	public Optional<VnfCompute> findComputeById(final UUID uuid) {
		return vnfComputeJpa.findById(uuid);
	}

	@Override
	public Optional<VnfExtCp> findExtCpById(final UUID uuid) {
		return vnfExtCpJpa.findById(uuid);
	}

	@Override
	public VduInstantiationLevel findByVnfComputeAndInstantiationLevel(final VnfCompute x, final String scaleInfoName) {
		return vnfComputeJpa.findByIdAndInstantiationLevelLevelName(x.getId(), scaleInfoName);
	}

	@Override
	public VnfPackage findById(final VnfPackage vnfPackage) {
		return vnfPackageJpa.findById(vnfPackage.getId()).orElseThrow(() -> new NotFoundException("VNF Package" + vnfPackage.getId() + " not found."));
	}

	@Override
	public VnfPackage findById(final UUID vnfPkgId) {
		return vnfPackageJpa.findById(vnfPkgId).orElseThrow(() -> new NotFoundException("VNF Package: " + vnfPkgId + " not found."));
	}

	@Override
	public List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfComputeAndLevel(final VnfPackage vnfPackage, final String level) {
		return vnfInstantiationLevelsJpa.findByVnfPackageAndLevelName(vnfPackage, level);
	}

	@Override
	public List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfPacckage(final VnfPackage vnfPackage) {
		return vnfInstantiationLevelsJpa.findDistinctScaleInfoNameByVnfPackage(vnfPackage);
	}

	@Override
	public VnfPackage save(final VnfPackage vnfPackage) {
		return vnfPackageJpa.save(vnfPackage);
	}

	@Override
	public Optional<VnfPackage> findByDescriptorId(final String descriptorId) {
		return vnfPackageJpa.findByDescriptorId(descriptorId);
	}

	@Override
	public Optional<VnfPackage> findByDescriptorIdFlavorIdVnfdVersion(final String descriptorId, final String flavorId, final String versionId) {
		return vnfPackageJpa.findByDescriptorIdAndFlavorIdAndVnfdVersion(descriptorId, flavorId, versionId);
	}

	@Override
	public Optional<VnfPackage> findByDescriptorIdAndSoftwareVersion(final String name, final String version) {
		return vnfPackageJpa.findByDescriptorIdAndVnfSoftwareVersion(name, version);
	}

	@Override
	public Set<VnfLinkPort> findVnfVirtualLinks(final VnfPackage vnfPackage) {
		return vnfLinkPortJpa.findByVnfPackage(vnfPackage);
	}

}
