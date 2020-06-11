package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfComputeAspectDeltaJpa;
import com.ubiqube.etsi.mano.jpa.VnfComputeJpa;
import com.ubiqube.etsi.mano.jpa.VnfExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiationLevelsJpa;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.jpa.VnfStorageJpa;
import com.ubiqube.etsi.mano.jpa.VnfVlJpa;

@Service
public class VnfPackageService {
	private final VnfComputeAspectDeltaJpa vnfComputeAspectDeltaJpa;

	private final VnfStorageJpa vnfStorageJpa;

	private final VnfVlJpa vnfVl;

	private final VnfComputeJpa vnfComputeJpa;

	private final VnfExtCpJpa vnfExtCpJpa;

	private final VnfPackageJpa vnfPackageJpa;

	private final VnfInstantiationLevelsJpa vnfInstantiationLevelsJpa;

	public VnfPackageService(final VnfComputeAspectDeltaJpa _vnfComputeAspectDeltaJpa, final VnfStorageJpa _vnfStorageJpa, final VnfVlJpa _vnfVl, final VnfComputeJpa _vnfComputeJpa, final VnfExtCpJpa _vnfExtCpJpa, final VnfPackageJpa _vnfPackageJpa, final VnfInstantiationLevelsJpa _vnfInstantiationLevelsJpa) {
		vnfComputeAspectDeltaJpa = _vnfComputeAspectDeltaJpa;
		vnfStorageJpa = _vnfStorageJpa;
		vnfVl = _vnfVl;
		vnfComputeJpa = _vnfComputeJpa;
		vnfExtCpJpa = _vnfExtCpJpa;
		vnfPackageJpa = _vnfPackageJpa;
		vnfInstantiationLevelsJpa = _vnfInstantiationLevelsJpa;
	}

	public List<VnfComputeAspectDelta> findAspectDeltaByAspectId(final VnfCompute vnfCompute, final String aspectName) {
		return vnfComputeAspectDeltaJpa.findByVnfComputeAndAspectName(vnfCompute, aspectName);
	}

	public Optional<VnfStorage> findStorageByName(final VnfPackage vnfPackage, final String name) {
		return vnfStorageJpa.findOneByVnfPackageAndToscaName(vnfPackage, name);
	}

	public Optional<VnfVl> findVirtualLnkById(final UUID uuid) {
		return vnfVl.findById(uuid);
	}

	public Optional<VnfStorage> findVirtualStorageById(final UUID uuid) {
		return vnfStorageJpa.findById(uuid);
	}

	public Optional<VnfCompute> findComputeById(final UUID uuid) {
		return vnfComputeJpa.findById(uuid);
	}

	public Optional<VnfExtCp> findExtCpById(final UUID uuid) {
		return vnfExtCpJpa.findById(uuid);
	}

	public VduInstantiationLevel findByVnfComputeAndInstantiationLevel(final VnfCompute x, final String scaleInfoName) {
		return vnfComputeJpa.findByIdAndInstantiationLevelLevelName(x.getId(), scaleInfoName);
	}

	public VnfPackage findById(final VnfPackage vnfPackage) {
		return vnfPackageJpa.findById(vnfPackage.getId()).orElseThrow(() -> new NotFoundException("VNF Package" + vnfPackage.getId() + " not found."));
	}

	public VnfPackage findById(final UUID vnfPkgId) {
		return vnfPackageJpa.findById(vnfPkgId).orElseThrow(() -> new NotFoundException("VNF Package" + vnfPkgId + " not found."));
	}

	public List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfComputeAndLevel(final VnfPackage vnfPackage, final String level) {
		return vnfInstantiationLevelsJpa.findByVnfPackageAndLevelName(vnfPackage, level);
	}

	public List<VnfInstantiationLevels> findVnfInstantiationLevelsByVnfPacckage(final VnfPackage vnfPackage) {
		return vnfInstantiationLevelsJpa.findDistinctScaleInfoNameByVnfPackage(vnfPackage);
	}

	public VnfPackage save(final VnfPackage vnfPackage) {
		return vnfPackageJpa.save(vnfPackage);
	}

	public Optional<VnfPackage> findByDescriptorId(final String descriptorId) {
		return vnfPackageJpa.findByDescriptorId(descriptorId);
	}

	public Optional<VnfPackage> findByDescriptorIdAndSoftwareVersion(final String name, final String version) {
		return vnfPackageJpa.findByDescriptorIdAndVnfSoftwareVersion(name, version);
	}

	public Optional<VnfPackage> findByDescriptorIdAndVnfSoftwareVersionAndFlavourId(final String flavour, final String name, final String version) {
		return vnfPackageJpa.findByDescriptorIdAndVnfSoftwareVersionAndFlavorId(name, version, flavour);
	}

}
