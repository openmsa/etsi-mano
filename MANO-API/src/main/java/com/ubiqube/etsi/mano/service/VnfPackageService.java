package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.Optional;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.jpa.VnfComputeAspectDeltaJpa;
import com.ubiqube.etsi.mano.jpa.VnfStorageJpa;

public class VnfPackageService {
	private final VnfComputeAspectDeltaJpa vnfComputeAspectDeltaJpa;
	private final VnfStorageJpa vnfStorageJpa;

	public VnfPackageService(final VnfComputeAspectDeltaJpa _vnfComputeAspectDeltaJpa, final VnfStorageJpa _vnfStorageJpa) {
		vnfComputeAspectDeltaJpa = _vnfComputeAspectDeltaJpa;
		vnfStorageJpa = _vnfStorageJpa;
	}

	public List<VnfComputeAspectDelta> findAspectDeltaByAspectId(final VnfCompute vnfCompute, final String aspectName) {
		return vnfComputeAspectDeltaJpa.findByVnfComputeAndAspectName(vnfCompute, aspectName);
	}

	public Optional<VnfStorage> findStorageByName(final VnfPackage vnfPackage, final String name) {
		return vnfStorageJpa.findOneByVnfPackageAndToscaName(vnfPackage, name);
	}
}
