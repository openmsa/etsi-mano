package com.ubiqube.etsi.mano.repository;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

public interface VnfInstancesRepository extends CrudRepositoryNg<VnfInstance> {

	boolean isInstantiate(@NotNull UUID vnfPkgId);

}
