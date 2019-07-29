package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfPackageRepository extends CrudRepository<VnfPkgInfo> {
	void storeObject(String _vnfPkgId, InputStream _stream, String _filename);

	void storeObject(String _vnfPkgId, Object _object, String _filename);

}
