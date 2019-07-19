package com.ubiqube.etsi.mano.repository;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfPackageRepository extends CrudRepository<VnfPkgInfo> {

	void storeObject(String _vnfPkgId, Object _object, String _filename);

}
