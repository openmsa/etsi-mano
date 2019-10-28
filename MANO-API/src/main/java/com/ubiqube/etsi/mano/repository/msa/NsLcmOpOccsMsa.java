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
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;

@Service
public class NsLcmOpOccsMsa extends AbstractGenericRepository<NsLcmOpOccsNsLcmOpOcc> implements NsLcmOpOccsRepository {

	public NsLcmOpOccsMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	@Override
	String setId(final NsLcmOpOccsNsLcmOpOcc _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	String getRoot() {
		return "Datafiles/NFVO/lcm-op-occs";
	}

	@Override
	String getFilename() {
		return "lcm-op-occs.json";
	}

	@Override
	Class<?> getClazz() {
		return NsLcmOpOccsNsLcmOpOcc.class;
	}

	@Override
	public NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(final String nsInstanceId, final LcmOperationTypeEnum state) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(nsInstanceId, state);
		save(lcmOpOccs);
		// Add newly created instance to Indexes.json
		final NsInstanceIndex nsInstanceIndex = loadObject(nsInstanceId, "indexes.json", NsInstanceIndex.class);
		nsInstanceIndex.addLcmOpOccs(lcmOpOccs);
		storeObject(nsInstanceId, "indexes.json", nsInstanceIndex);
		return lcmOpOccs;
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String lcmOpOccsId, final String processId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = get(lcmOpOccsId);
		final NsInstanceIndex nsInstanceIndex = loadObject(lcmOpOccs.getNsInstanceId(), "indexes.json", NsInstanceIndex.class);
		final NsLcmOpOccsIndex lcmIdx = nsInstanceIndex.getLcmOpOccs(lcmOpOccsId);
		lcmIdx.setProcessId(processId);
		storeObject(lcmOpOccs.getNsInstanceId(), "indexes.json", nsInstanceIndex);

	}

}
