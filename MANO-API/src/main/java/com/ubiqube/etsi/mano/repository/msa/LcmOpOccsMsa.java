package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.repository.LcmOpOccsRepository;

@Service
public class LcmOpOccsMsa extends AbstractGenericRepository<NsLcmOpOccsNsLcmOpOcc> implements LcmOpOccsRepository {

	public LcmOpOccsMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
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

}
