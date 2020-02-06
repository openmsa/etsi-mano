package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nsd.NsdPkgIndex;
import com.ubiqube.etsi.mano.model.nsd.NsdPkgInstance;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;

@Profile("!RDBMS")
public class NsInstanceMsa extends AbstractGenericRepository<NsdInstance> implements NsInstanceRepository {
	private static final String REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH = "Datafiles/NFVO/ns_instances";
	private final NsdRepository nsdRepository;

	public NsInstanceMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final NsdRepository _nsdRepository) {
		super(_mapper, _repositoryService, _jsonFilter);
		nsdRepository = _nsdRepository;
	}

	@Override
	String setId(final NsdInstance _entity) {
		final String id = _entity.getId().toString();
		if (null == id) {
			_entity.setId(UUID.randomUUID());
		}

		return _entity.getId().toString();
	}

	@Override
	Class<?> getClazz() {
		return NsdInstance.class;
	}

	@Override
	String getRoot() {
		return REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "nsInstance.json";
	}

	@Override
	public void changeNsdUpdateState(final NsdInstance nsInstance, final InstantiationStateEnum state) {
		nsInstance.setNsState(state);
		save(nsInstance);
	}

	@Override
	public NsdInstance save(final NsdInstance _entity) {
		final NsdInstance nsInstance = save(_entity);
		final NsdPkgIndex nsdIndex = nsdRepository.loadObject(nsInstance.getNsdId(), "indexes.json", NsdPkgIndex.class);
		nsdIndex.addNsdPkgInstance(new NsdPkgInstance(nsInstance.getId().toString()));
		nsdRepository.storeObject(nsInstance.getNsdId(), "indexes.json", nsdIndex);
		return nsInstance;
	}

}
