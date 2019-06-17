package com.ubiqube.etsi.mano.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

/**
 * Implementation of a repository for a VNFPackage document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Repository
public class VnfPackageRepository extends AbstractGenericRepository<VnfPkgInfo> {

	private final static String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";

	@Override
	String getUriForId(String _id) {
		return REPOSITORY_NVFO_DATAFILE_BASE_PATH + "/" + _id + "/vnfPkgInfo.json";
	}

	@Override
	String setId(VnfPkgInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	Class<?> getClazz() {
		return VnfPkgInfo.class;
	}

}
