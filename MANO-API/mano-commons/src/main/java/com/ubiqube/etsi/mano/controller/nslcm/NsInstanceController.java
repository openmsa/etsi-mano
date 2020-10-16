package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;

public interface NsInstanceController {

	List<NsdInstance> nsInstancesGet(String filter);

	void nsInstancesNsInstanceIdDelete(UUID id);

	NsdInstance nsInstancesNsInstanceIdGet(UUID id);

	NsdInstance nsInstancesNsInstanceIdHealPost(UUID id);

	NsdInstance nsInstancesNsInstanceIdScalePost(UUID id);

	void nsInstancesNsInstanceIdUpdatePost(UUID id);

}