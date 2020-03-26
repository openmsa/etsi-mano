package com.ubiqube.etsi.mano.service.pkg;

import java.util.List;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.service.event.ProviderData;

public interface PackageProvider {

	List<VnfPackageSoftwareImageInfo> getSoftwareImages();

	ProviderData getProviderPadata();

	List<VnfPackageArtifactInfo> getAdditionalArtefacts();

}
