package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdInfo;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

@Profile("!RDBMS")
public class PnfDescriptiorsMsa extends AbstractGenericRepository<PnfdInfo> implements PnfdInfoRepository {

	public PnfDescriptiorsMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	@Override
	String setId(final PnfdInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	String getRoot() {
		return "Datafiles/NFVO/pnfd";
	}

	@Override
	String getFilename() {
		return "pnfd.json";
	}

	@Override
	Class<?> getClazz() {
		return PnfdInfo.class;
	}

}
