package com.ubiqube.etsi.mano.repository;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;

public interface VnfInstancesRepository extends CrudRepository<VnfInstance> {

	<T, U extends Class> T loadObject(@NotNull final String _id, final U t, final String _filename);

}
