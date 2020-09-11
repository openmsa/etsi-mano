package com.ubiqube.etsi.mano.nfvo.controller.nsd;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

@Service
public class NsdController {

	private static final Logger LOG = LoggerFactory.getLogger(NsdController.class);
	private final NsdRepository nsdRepository;
	private final Patcher patcher;
	private final VnfPackageRepository vnfPackageRepository;
	private final EventManager eventManager;

	public NsdController(final NsdRepository _nsdRepository, final VnfPackageRepository _vnfPackageRepository, final Patcher _patcher, final EventManager _eventManager) {
		nsdRepository = _nsdRepository;
		vnfPackageRepository = _vnfPackageRepository;
		patcher = _patcher;
		eventManager = _eventManager;
		LOG.info("Starting NSD Management SOL005 Controller.");
	}

	public List<NsdPackage> nsDescriptorsGet(final String filter) {
		return nsdRepository.query(filter);
	}

	public void nsDescriptorsNsdInfoIdDelete(final UUID id) {
		final NsdPackage nsdPackage = nsdRepository.get(id);
		ensureDisabled(nsdPackage);
		ensureNotInUse(nsdPackage);
		nsdRepository.delete(id);
		// NsdDeletionNotification OSS/BSS
	}

	public NsdPackage nsDescriptorsNsdInfoIdGet(final UUID id) {
		return nsdRepository.get(id);
	}

	public byte[] nsDescriptorsNsdInfoIdNsdContentGet(final UUID id) {
		final NsdPackage nsdInfo = nsdRepository.get(id);
		ensureIsOnboarded(nsdInfo);
		return nsdRepository.getBinary(id, "nsd");
	}

	public void nsDescriptorsNsdInfoIdNsdContentPut(final UUID id, final InputStream is) {
		final NsdPackage nsdInfo = nsdRepository.get(id);
		ensureNotOnboarded(nsdInfo);
		nsdRepository.storeBinary(id, "nsd", is);
		eventManager.sendAction(ActionType.NSD_PKG_ONBOARD_FROM_BYTES, nsdInfo.getId(), new HashMap<>());
	}

	public NsdPackage nsDescriptorsNsdInfoIdPatch(final UUID id, final String body) {
		final NsdPackage nsdPkgInfo = nsdRepository.get(id);
		patcher.patch(body, nsdPkgInfo);
		// NsdChangeNotification OSS/BSS
		return nsdRepository.save(nsdPkgInfo);
	}

	public NsdPackage nsDescriptorsPost(final Map<String, String> userDefinedData) {
		final NsdPackage nsdPackage = new NsdPackage();
		nsdPackage.setUserDefinedData(userDefinedData);
		nsdPackage.setNsdOnboardingState(OnboardingStateType.CREATED);
		nsdPackage.setNsdOperationalState(PackageOperationalState.DISABLED);
		return nsdRepository.save(nsdPackage);
	}
}
