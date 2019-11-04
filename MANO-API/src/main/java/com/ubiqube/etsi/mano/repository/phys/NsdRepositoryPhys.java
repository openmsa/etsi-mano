package com.ubiqube.etsi.mano.repository.phys;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NsdRepository;

public class NsdRepositoryPhys extends GenaricBinaryRepository<NsDescriptorsNsdInfo> implements NsdRepository {

	public NsdRepositoryPhys(final String _root, final ObjectMapper _objectMapper, final JsonFilter _jsonFilter, final Low low) {
		super(_root, _objectMapper, _jsonFilter, low);
	}

	@Override
	protected String setId(final NsDescriptorsNsdInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	protected Class<NsDescriptorsNsdInfo> getClazz() {
		return NsDescriptorsNsdInfo.class;
	}

	@Override
	protected String getFilename() {
		return "nsd.json";
	}

	@Override
	protected String getDir() {
		return "nsd";
	}

	@Override
	public void changeNsdUpdateState(final NsDescriptorsNsdInfo nsdInfo, final NsdUsageStateEnum inUse) {
		// TODO Auto-generated method stub

	}

}
