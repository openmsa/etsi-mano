package com.ubiqube.etsi.mano.repository;

import java.util.List;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;

public interface NsInstanceRepository extends CrudRepository<NsInstancesNsInstance> {

	List<NsInstancesNsInstance> query();

}
