package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.NsInstanceIndex;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpOccsIndex;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;

/**
 * An implementation of a repository for a NSD Document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsdPackageMsa extends AbstractGenericRepository<NsDescriptorsNsdInfo> implements NsdRepository {
	private final NsLcmOpOccsRepository lcmOpOccsRepository;

	public NsdPackageMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final NsLcmOpOccsRepository _lcmOpOccsRepository) {
		super(_mapper, _repositoryService, _jsonFilter);
		lcmOpOccsRepository = _lcmOpOccsRepository;
	}

	private static final String REPOSITORY_NVFO_NSD_DATAFILE_BASE_PATH = "Datafiles/NFVO/nsd";

	@Override
	String setId(final NsDescriptorsNsdInfo _entity) {
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

	@Override
	public NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(final String nsInstanceId, final LcmOperationTypeEnum state) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(nsInstanceId, state);
		lcmOpOccsRepository.save(lcmOpOccs);
		// Add newly created instance to Indexes.json
		final NsInstanceIndex nsInstanceIndex = loadObject(nsInstanceId, "indexes.json", NsInstanceIndex.class);
		nsInstanceIndex.addLcmOpOccs(lcmOpOccs);
		storeObject(nsInstanceId, "indexes.json", nsInstanceIndex);
		return lcmOpOccs;
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String lcmOpOccsId, final String processId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = lcmOpOccsRepository.get(lcmOpOccsId);
		final NsInstanceIndex nsInstanceIndex = loadObject(lcmOpOccs.getNsInstanceId(), "indexes.json", NsInstanceIndex.class);
		final NsLcmOpOccsIndex lcmIdx = nsInstanceIndex.getLcmOpOccs(lcmOpOccsId);
		lcmIdx.setProcessId(processId);
		storeObject(lcmOpOccs.getNsInstanceId(), "indexes.json", nsInstanceIndex);
	}

	@Override
	public void changeNsdUpdateState(final NsDescriptorsNsdInfo nsdInfo, final NsdUsageStateEnum state) {
		nsdInfo.setNsdUsageState(state);
		save(nsdInfo);
	}

}
