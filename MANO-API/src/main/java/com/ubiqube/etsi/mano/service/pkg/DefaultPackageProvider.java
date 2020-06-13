package com.ubiqube.etsi.mano.service.pkg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
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
	public Set<SoftwareImage> getSoftwareImages(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<AdditionalArtifact> getAdditionalArtefacts(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<VnfCompute> getVnfComputeNodes(final Map<String, String> parameters) {
		final HashSet<VnfCompute> set = new HashSet<>();
		// Create One VDU. for one call on OS createStack.
		final VnfCompute vnfCompute = new VnfCompute();
		set.add(vnfCompute);
		return set;
	}

	@Override
	public Set<VnfStorage> getVnfStorages(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<VnfVl> getVnfVirtualLinks(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<VnfLinkPort> getVnfVduCp(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<VnfExtCp> getVnfExtCp(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<ScalingAspect> getScalingAspects(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public List<InstantiationLevels> getInstatiationLevels(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public List<VduInstantiationLevels> getVduInstantiationLevels(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public List<VduInitialDelta> getVduInitialDelta(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public List<VduScalingAspectDeltas> getVduScalingAspectDeltas(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public NsInformations getNsInformations(final Map<String, String> userData) {
		return new NsInformations();
	}

	@Override
	public Set<NsVirtualLink> getNsVirtualLink(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<NsSap> getNsSap(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<SecurityGroupAdapter> getSecurityGroups(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<String> getNestedNsd(final Map<String, String> userData) {
		return new HashSet<>();
	}

	@Override
	public Set<String> getVnfd(final Map<String, String> userData) {
		return new HashSet<>();
	}

}
