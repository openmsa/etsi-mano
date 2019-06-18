package com.ubiqube.etsi.mano.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;

/**
 * An implementation of a repository for a NSD Document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Repository
public class NsdRepository extends AbstractGenericRepository<NsDescriptorsNsdInfo> {
	private static final String REPOSITORY_NVFO_NSD_DATAFILE_BASE_PATH = "Datafiles/NFVO/nsd";

	@Override
	String getUriForId(String _id) {
		return REPOSITORY_NVFO_NSD_DATAFILE_BASE_PATH + "/" + _id + "/nsd.json";
	}

	@Override
	String setId(NsDescriptorsNsdInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	Class<?> getClazz() {
		return NsDescriptorsNsdInfo.class;
	}

}
