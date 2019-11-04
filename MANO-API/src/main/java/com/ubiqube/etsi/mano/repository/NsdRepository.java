package com.ubiqube.etsi.mano.repository;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;

public interface NsdRepository extends CrudRepository<NsDescriptorsNsdInfo>, BinaryRepository {
	void changeNsdUpdateState(NsDescriptorsNsdInfo nsdInfo, @Nonnull NsdUsageStateEnum state);
}
