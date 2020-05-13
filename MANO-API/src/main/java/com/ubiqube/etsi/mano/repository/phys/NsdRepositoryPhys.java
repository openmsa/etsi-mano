package com.ubiqube.etsi.mano.repository.phys;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.NsdRepository;

public class NsdRepositoryPhys extends GenericBinaryRepository<NsdInfo> implements NsdRepository {

	public NsdRepositoryPhys(final ObjectMapper _objectMapper, final JsonFilter _jsonFilter, final Low low, final NamingStrategy _namingStrategy) {
		super(_objectMapper, _jsonFilter, low, _namingStrategy);
	}

	@Override
	protected UUID setId(final NsdInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return UUID.fromString(_entity.getId());
	}

	@Override
	protected Class<NsdInfo> getClazz() {
		return NsdInfo.class;
	}

	@Override
	protected String getFilename() {
		return "nsd.json";
	}

	@Override
	public void changeNsdUpdateState(final NsdInfo nsdInfo, final NsdUsageStateType inUse) {
		// TODO Auto-generated method stub

	}

}
