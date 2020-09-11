package com.ubiqube.etsi.mano.controller.vnf;

import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public interface VnfPackageController {

	VnfPackage vnfPackagesPost(Map<String, String> userData);

	void vnfPackagesVnfPkgIdDelete(UUID id);

	VnfPackage vnfPackagesVnfPkgIdPatch(UUID id, String body);

	void vnfPackagesVnfPkgIdPackageContentPut(UUID id);

	void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(UUID id);

}