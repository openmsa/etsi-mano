package com.ubiqube.etsi.mano.controller.nsd;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.factory.PnfFactory;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

@Service
public class PnfdControllerImpl implements PnfdController {
	private final PnfdInfoRepository pnfdInfoRepository;

	public PnfdControllerImpl(final PnfdInfoRepository _pnfdInfoRepository) {
		pnfdInfoRepository = _pnfdInfoRepository;
	}

	@Override
	public List<PnfDescriptor> pnfDescriptorsGet(final String filter) {
		return pnfdInfoRepository.query(filter);
	}

	@Override
	public void pnfDescriptorsPnfdInfoIdDelete(final UUID id) {
		pnfdInfoRepository.delete(id);
	}

	@Override
	public PnfDescriptor pnfDescriptorsPnfdInfoIdGet(final UUID id) {
		return pnfdInfoRepository.get(id);
	}

	@Override
	public PnfDescriptor pnfDescriptorsPost(final Map<String, Object> userDefinedData) {
		final PnfDescriptor pnfdDb = PnfFactory.createPnfDescriptorsPnfdInfo(userDefinedData);
		return pnfdInfoRepository.save(pnfdDb);
	}
}
