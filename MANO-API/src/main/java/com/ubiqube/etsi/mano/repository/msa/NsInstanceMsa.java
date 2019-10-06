package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.NsInstanceIndex;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpOccsIndex;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;

@Service
public class NsInstanceMsa extends AbstractGenericRepository<NsInstance> implements NsInstanceRepository {
	private static final String REPOSITORY_NS_INSTANCE_DATAFILE_BASE_PATH = "Datafiles/NFVO/ns_instances";
	private final NsLcmOpOccsRepository lcmOpOccsRepository;

	public NsInstanceMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final NsLcmOpOccsRepository _lcmOpOccsRepository) {
		super(_mapper, _repositoryService, _jsonFilter);
		lcmOpOccsRepository = _lcmOpOccsRepository;
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
	public NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(final String nsInstanceId, final LcmOperationTypeEnum state) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(nsInstanceId, state);
		lcmOpOccsRepository.save(lcmOpOccs);
		// Add newly created instance to Indexes.json
		final NsInstanceIndex nsInstanceIndex = loadObject(nsInstanceId, NsInstanceIndex.class, "indexes.json");
		nsInstanceIndex.addLcmOpOccs(lcmOpOccs);
		storeObject(nsInstanceId, nsInstanceIndex, "indexes.json");
		return lcmOpOccs;
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String lcmOpOccsId, final String processId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = lcmOpOccsRepository.get(lcmOpOccsId);
		final NsInstanceIndex nsInstanceIndex = loadObject(lcmOpOccs.getNsInstanceId(), NsInstanceIndex.class, "indexes.json");
		final NsLcmOpOccsIndex lcmIdx = nsInstanceIndex.getLcmOpOccs(lcmOpOccsId);
		lcmIdx.setProcessId(processId);
		storeObject(lcmOpOccs.getNsInstanceId(), nsInstanceIndex, "indexes.json");
	}

	@Override
	public void changeNsdUpdateState(final NsInstance nsInstance, final NsStateEnum state) {
		nsInstance.setNsState(state);
		save(nsInstance);
	}

}
