package com.ubiqube.etsi.mano.repository;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;

public interface NsInstanceRepository extends CrudRepository<NsdInstance> {

	void changeNsdUpdateState(NsdInstance nsInstance, InstantiationStateEnum notInstantiated);

}
