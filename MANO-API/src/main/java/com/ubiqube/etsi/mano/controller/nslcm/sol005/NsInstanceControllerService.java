package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.model.nslcm.sol005.CreateNsRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.InstantiateNsRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.TerminateNsRequest;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;
import com.ubiqube.etsi.mano.service.NsdPackageService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

import ma.glasnost.orika.MapperFacade;

@Service
public class NsInstanceControllerService {

	private static final Logger LOG = LoggerFactory.getLogger(NsInstanceControllerService.class);

	private final NsdPackageService nsdPackageService;

	private final NsInstanceService nsInstanceService;

	private final NsLcmOpOccsService nsLcmOpOccsService;

	private final EventManager eventManager;

	private final MapperFacade mapper;

	public NsInstanceControllerService(final NsdPackageService nsdPackageService, final NsInstanceService nsInstanceService, final NsLcmOpOccsService _nsLcmOpOccsService, final EventManager _eventManager, final MapperFacade _mapper) {
		super();
		this.nsdPackageService = nsdPackageService;
		this.nsInstanceService = nsInstanceService;
		nsLcmOpOccsService = _nsLcmOpOccsService;
		eventManager = _eventManager;
		mapper = _mapper;
	}

	public NsdInstance createNsd(final CreateNsRequest req) {
		final UUID nsdId = UUID.fromString(req.getNsdId());
		final NsdPackage nsd = nsdPackageService.findById(nsdId);
		ensureIsOnboarded(nsd);
		ensureIsEnabled(nsd);
		nsd.setNsdUsageState(PackageUsageStateType.IN_USE);
		nsdPackageService.save(nsd);

		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsInstanceName(req.getNsName());
		nsInstance.setNsInstanceDescription(req.getNsDescription());
		nsInstance.setNsdInfo(nsd);
		final NsdInstance nsInstanceTmp = nsInstanceService.save(nsInstance);

		final List<VnfInstance> vnfInstances = new ArrayList<>();

		nsd.getNestedNsdInfoIds().forEach(x -> {
			// create nested instance.
			final CreateNsRequest reqNested = new CreateNsRequest();
			reqNested.setNsdId(x.getChild().getId().toString());
			LOG.debug("Recursing: {}", reqNested);
			final NsdInstance nsIn = createNsd(reqNested);
			nsInstanceTmp.addNestedNsInstance(nsIn);
		});
		nsInstanceTmp.setVnfInstance(vnfInstances);
		return nsInstanceService.save(nsInstanceTmp);
	}

	public NsLcmOpOccs instantiate(final UUID nsUuid, final InstantiateNsRequest req) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(nsUuid);
		ensureNotInstantiated(nsInstanceDb);
		final NsLcmOpOccs nsLcm = nsLcmOpOccsService.createLcmOpOccs(nsInstanceDb, NsLcmOpType.INSTANTIATE);

		mapper.map(req, nsInstanceDb);
		nsInstanceService.save(nsInstanceDb);
		eventManager.sendAction(ActionType.NS_INSTANTIATE, nsLcm.getId(), new HashMap<String, Object>());
		return nsLcm;
	}

	public NsLcmOpOccs terminate(final UUID nsInstanceUuid, final TerminateNsRequest request) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(nsInstanceUuid);
		ensureInstantiated(nsInstanceDb);
		final NsLcmOpOccs nsLcm = nsLcmOpOccsService.createLcmOpOccs(nsInstanceDb, NsLcmOpType.TERMINATE);
		// XXX we can use quartz cron job for terminationTime.
		eventManager.sendAction(ActionType.NS_TERMINATE, nsLcm.getId(), new HashMap<String, Object>());
		return nsLcm;
	}
}
