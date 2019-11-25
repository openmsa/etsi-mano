package com.ubiqube.etsi.mano.service.event;

import java.util.List;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo;

public interface PackageProvider {

	List<VnfPackageSoftwareImageInfo> getSoftwareImages();

}
