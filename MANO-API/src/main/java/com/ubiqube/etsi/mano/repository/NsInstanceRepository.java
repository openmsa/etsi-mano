package com.ubiqube.etsi.mano.repository;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;

public interface NsInstanceRepository extends CrudRepository<NsInstance> {

	void changeNsdUpdateState(NsInstance nsInstance, InstantiationStateEnum notInstantiated);

}
