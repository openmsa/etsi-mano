package com.ubiqube.etsi.mano.service.pkg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.service.event.ProviderData;

import tosca.policies.nfv.InstantiationLevels;
import tosca.policies.nfv.VduInitialDelta;
import tosca.policies.nfv.VduInstantiationLevels;
import tosca.policies.nfv.VduScalingAspectDeltas;

public class DefaultPackageProvider implements PackageProvider {
	@Override
	public ProviderData getProviderPadata() {
		final ProviderData providerData = new ProviderData();
		providerData.setVnfProductName("Ubiqube");
		providerData.setVnfProvider("Ubiqube Openstack HEAT.");
		providerData.setVnfSoftwareVersion("0.0.1");
		providerData.setVnfVersion("0.0.1");
		return providerData;
	}

	@Override
	public Set<SoftwareImage> getSoftwareImages() {
		return new HashSet<>();
	}

	@Override
	public Set<AdditionalArtifact> getAdditionalArtefacts() {
		return new HashSet<>();
	}

	@Override
	public Set<VnfCompute> getVnfComputeNodes() {
		final HashSet<VnfCompute> set = new HashSet<>();
		// Create One VDU. for one call on OS createStack.
		final VnfCompute vnfCompute = new VnfCompute();
		set.add(vnfCompute);
		return set;
	}

	@Override
	public Set<VnfStorage> getVnfStorages() {
		return new HashSet<>();
	}

	@Override
	public Set<VnfVl> getVnfVirtualLinks() {
		return new HashSet<>();
	}

	@Override
	public Set<VnfLinkPort> getVnfVduCp() {
		return new HashSet<>();
	}

	@Override
	public Set<VnfExtCp> getVnfExtCp() {
		return new HashSet<>();
	}

	@Override
	public Set<ScalingAspect> getScalingAspects() {
		return new HashSet<>();
	}

	@Override
	public List<InstantiationLevels> getInstatiationLevels() {
		return new ArrayList<>();
	}

	@Override
	public List<VduInstantiationLevels> getVduInstantiationLevels() {
		return new ArrayList<>();
	}

	@Override
	public List<VduInitialDelta> getVduInitialDelta() {
		return new ArrayList<>();
	}

	@Override
	public List<VduScalingAspectDeltas> getVduScalingAspectDeltas() {
		return new ArrayList<>();
	}

}
