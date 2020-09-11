package com.ubiqube.etsi.mano.nfvo.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;

public class NsLcmController {
	private final NsInstanceRepository nsInstanceRepository;
	private final NsLcmOpOccsService lcmOpOccsService;
	private final NsInstanceService nsInstanceService;

	public NsLcmController(final NsInstanceRepository _nsInstanceRepository, final NsInstanceService _nsInstanceService, final NsLcmOpOccsService _lcmOpOccsService) {
		nsInstanceRepository = _nsInstanceRepository;
		nsInstanceService = _nsInstanceService;
		lcmOpOccsService = _lcmOpOccsService;
	}

	public List<NsdInstance> nsInstancesGet(final String filter) {
		return nsInstanceRepository.query(filter);
	}

	public void nsInstancesNsInstanceIdDelete(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceRepository.get(id);
		ensureNotInstantiated(nsInstanceDb);
		nsInstanceService.delete(id);
	}

	public NsdInstance nsInstancesNsInstanceIdGet(final UUID id) {
		return nsInstanceRepository.get(id);
	}

	public NsdInstance nsInstancesNsInstanceIdHealPost(final UUID id) {
		final NsdInstance nsInstance = nsInstanceRepository.get(id);
		ensureInstantiated(nsInstance);
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstance, NsdChangeType.HEAL);
		lcmOpOccsService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	public NsdInstance nsInstancesNsInstanceIdScalePost(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceRepository.get(id);
		ensureInstantiated(nsInstanceDb);
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceDb, NsdChangeType.SCALE);
		lcmOpOccsService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	public void nsInstancesNsInstanceIdUpdatePost(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceRepository.get(id);
		ensureInstantiated(nsInstanceDb);
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceDb, NsdChangeType.UPDATE);
		lcmOpOccsService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}
}