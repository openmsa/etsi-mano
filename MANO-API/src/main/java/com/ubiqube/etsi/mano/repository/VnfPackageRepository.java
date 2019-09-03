package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public interface VnfPackageRepository extends CrudRepository<VnfPkgInfo> {

	<T, U extends Class> T loadObject(@NotNull final String _id, final U t, final String _filename);
}
