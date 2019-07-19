package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.repository.NsdRepository;

/**
 * An implementation of a repository for a NSD Document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Repository
public class NsPackageMsa extends AbstractGenericRepository<NsDescriptorsNsdInfo> implements NsdRepository {
	@Inject
	public NsPackageMsa(ObjectMapper _mapper, RepositoryService _repositoryService) {
		super(_mapper, _repositoryService);
	}

	private static final String REPOSITORY_NVFO_NSD_DATAFILE_BASE_PATH = "Datafiles/NFVO/nsd";

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

	@Override
	String getRoot() {
		return REPOSITORY_NVFO_NSD_DATAFILE_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "nsd.json";
	}

}
