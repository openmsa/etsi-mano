package com.ubiqube.etsi.mano.repository;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdUsageStateType;

public interface NsdRepository extends CrudRepository<NsdInfo>, BinaryRepository {
	void changeNsdUpdateState(NsdInfo nsdInfo, @Nonnull NsdUsageStateType state);
}
