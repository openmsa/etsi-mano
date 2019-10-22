package com.ubiqube.etsi.mano.service.event;

import java.util.List;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoSoftwareImages;

public interface PackageProvider {

	List<VnfPackagesVnfPkgInfoSoftwareImages> getSoftwareImages();

}
