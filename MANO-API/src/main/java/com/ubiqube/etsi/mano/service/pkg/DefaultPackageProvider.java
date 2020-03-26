package com.ubiqube.etsi.mano.service.pkg;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.service.event.ProviderData;

public class DefaultPackageProvider implements PackageProvider {

	@Override
	public List<VnfPackageSoftwareImageInfo> getSoftwareImages() {
		return new ArrayList<>();
	}

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
	public List<VnfPackageArtifactInfo> getAdditionalArtefacts() {
		return new ArrayList<>();
	}

}
