package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;

@Service
public class NsInstanceMsa extends AbstractGenericRepository<NsInstance> implements NsInstanceRepository {
	private static final String REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH = "Datafiles/NFVO/ns_instances";

	public NsInstanceMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	@Override
	String setId(final NsInstance _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	Class<?> getClazz() {
		return NsInstance.class;
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
	public void changeNsdUpdateState(final NsInstance nsInstance, final InstantiationStateEnum state) {
		nsInstance.setNsState(state);
		save(nsInstance);
	}

}
