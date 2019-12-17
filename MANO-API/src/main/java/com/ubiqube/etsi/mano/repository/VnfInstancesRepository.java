package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.VnfInstance;

public interface VnfInstancesRepository extends CrudRepository<VnfInstance>, BinaryRepository {

	boolean isInstantiate(@NotNull String vnfPkgId);

}
