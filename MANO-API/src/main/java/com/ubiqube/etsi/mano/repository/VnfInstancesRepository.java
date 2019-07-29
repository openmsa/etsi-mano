package com.ubiqube.etsi.mano.repository;

import java.util.List;

import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;

public interface VnfInstancesRepository extends CrudRepository<VnfInstance> {

	List<VnfInstance> query();

}
