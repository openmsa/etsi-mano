package com.ubiqube.etsi.mano.repository;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;

public interface NsdRepository extends CrudRepository<NsdPackage>, BinaryRepository {
	// XXX To move -> NsdPackageService.
	void changeNsdUpdateState(NsdPackage nsdPackage, @Nonnull PackageUsageStateType state);
}
