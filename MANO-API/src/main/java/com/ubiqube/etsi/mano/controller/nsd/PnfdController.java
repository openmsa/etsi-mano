package com.ubiqube.etsi.mano.controller.nsd;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.factory.PnfFactory;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

public class PnfdController {
	private final PnfdInfoRepository pnfdInfoRepository;

	public PnfdController(final PnfdInfoRepository _pnfdInfoRepository) {
		pnfdInfoRepository = _pnfdInfoRepository;
	}

	public List<PnfDescriptor> pnfDescriptorsGet(final String filter) {
		return pnfdInfoRepository.query(filter);
	}

	public void pnfDescriptorsPnfdInfoIdDelete(final UUID id) {
		pnfdInfoRepository.delete(id);
	}

	public PnfDescriptor pnfDescriptorsPnfdInfoIdGet(final UUID id) {
		return pnfdInfoRepository.get(id);
	}

	public PnfDescriptor pnfDescriptorsPost(final Map<String, Object> userDefinedData) {
		final PnfDescriptor pnfdDb = PnfFactory.createPnfDescriptorsPnfdInfo(userDefinedData);
		return pnfdInfoRepository.save(pnfdDb);
	}
}
