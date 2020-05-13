package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.NsInstanceIndex;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpOccsIndex;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;

@Profile("!RDBMS")
public class NsLcmOpOccsMsa extends AbstractGenericRepository<NsLcmOpOcc> implements NsLcmOpOccsRepository {

	private final NsdPackageMsa nsdPackageMsa;

	public NsLcmOpOccsMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final NsdPackageMsa _nsdPackageMsa) {
		super(_mapper, _repositoryService, _jsonFilter);
		nsdPackageMsa = _nsdPackageMsa;
	}

	@Override
	UUID setId(final NsLcmOpOcc _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return UUID.fromString(_entity.getId());
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
		return NsLcmOpOcc.class;
	}

	@Override
	public NsLcmOpOcc createLcmOpOccs(final UUID nsInstanceId, final NsLcmOpType state) {
		final NsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceId, state);
		save(lcmOpOccs);
		// Add newly created instance to Indexes.json
		final NsInstanceIndex nsInstanceIndex = nsdPackageMsa.loadObject(nsInstanceId, "indexes.json", NsInstanceIndex.class);
		nsInstanceIndex.addLcmOpOccs(lcmOpOccs);
		nsdPackageMsa.storeObject(nsInstanceId, "indexes.json", nsInstanceIndex);
		return lcmOpOccs;
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final UUID lcmOpOccsId, final String processId) {
		final NsLcmOpOcc lcmOpOccs = get(lcmOpOccsId);
		final NsInstanceIndex nsInstanceIndex = nsdPackageMsa.loadObject(UUID.fromString(lcmOpOccs.getNsInstanceId()), "indexes.json", NsInstanceIndex.class);
		final NsLcmOpOccsIndex lcmIdx = nsInstanceIndex.getLcmOpOccs(lcmOpOccsId);
		lcmIdx.setProcessId(processId);
		nsdPackageMsa.storeObject(UUID.fromString(lcmOpOccs.getNsInstanceId()), "indexes.json", nsInstanceIndex);

	}

}
