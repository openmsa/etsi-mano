package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.repository.NsdRepository;

/**
 * An implementation of a repository for a NSD Document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Profile("!RDBMS")
public class NsdPackageMsa extends AbstractGenericRepository<NsdInfo> implements NsdRepository {

	public NsdPackageMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	private static final String REPOSITORY_NVFO_NSD_DATAFILE_BASE_PATH = "Datafiles/NFVO/nsd";

	@Override
	UUID setId(final NsdInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return UUID.fromString(_entity.getId());
	}

	@Override
	Class<?> getClazz() {
		return NsdInfo.class;
	}

	@Override
	String getRoot() {
		return REPOSITORY_NVFO_NSD_DATAFILE_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "nsd.json";
	}

	@Override
	public NsdInfo save(final NsdInfo _entity) {
		final NsdInfo nsdDescriptor = super.save(_entity);
		storeObject(UUID.fromString(nsdDescriptor.getId()), "indexes.json", new VnfPkgIndex());
		return nsdDescriptor;
	}

	@Override
	public void changeNsdUpdateState(final NsdInfo nsdInfo, final NsdUsageStateType state) {
		nsdInfo.setNsdUsageState(state);
		save(nsdInfo);
	}

}
